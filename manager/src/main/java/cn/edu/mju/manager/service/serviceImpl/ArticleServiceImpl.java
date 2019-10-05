package cn.edu.mju.manager.service.serviceImpl;

import cn.edu.mju.common.dto.ArticleArchivesDTO;
import cn.edu.mju.common.dto.ArticleDTO;
import cn.edu.mju.common.entity.Article;
import cn.edu.mju.common.entity.ArticleTag;
import cn.edu.mju.common.entity.Category;
import cn.edu.mju.common.entity.Tag;
import cn.edu.mju.common.exception.RestException;
import cn.edu.mju.common.util.SQLFilter;
import cn.edu.mju.manager.mapper.ArticleMapper;
import cn.edu.mju.manager.service.ArticleService;
import cn.edu.mju.manager.service.ArticleTagService;
import cn.edu.mju.manager.service.CategoryService;
import cn.edu.mju.manager.service.TagService;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * @author <a href="https://github.com/haidilaohotpot">cheng</a>
 * @since 1.0.0 2019/9/26
 */
@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper,Article> implements ArticleService {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ArticleTagService articleTagService;

    @Autowired
    private TagService tagService;

    @Override
    public ArticleDTO queryPage(Map<String, Object> params) {

        QueryWrapper<Article> queryWrapper = new QueryWrapper<>();

        String categoryIdStr = (String) params.get("categoryId");

        if (StringUtils.isNotBlank(categoryIdStr)){
            Integer categoryId = Integer.parseInt(categoryIdStr);
            queryWrapper.eq("category_id", categoryId);
        }

        String year = (String) params.get("year");
        String month = (String) params.get("month");

        if (StringUtils.isNotBlank(year) && StringUtils.isNotBlank(month)) {
            queryWrapper.eq("year(create_time)", year);
            queryWrapper.eq("month(create_time)", month);
        }

        Page<Article> page = this.getPage(params);



        //防止SQL注入（因为sidx、order是通过拼接SQL实现排序的，会有SQL注入风险）
        String sidx = SQLFilter.sqlInject((String) params.get("sidx"));
        String order = SQLFilter.sqlInject((String) params.get("order"));

        //排序
        if (StringUtils.isNotBlank(sidx) && StringUtils.isNotBlank(order)) {
            queryWrapper.orderByDesc(sidx);
        }


        IPage<Article> articleIPage = this.page(page, queryWrapper);

        ArticleDTO articleDTO = new ArticleDTO();
        articleDTO.setCurrent((int) articleIPage.getCurrent());

        articleDTO.setData(articleIPage.getRecords());

        articleDTO.setPages((int) articleIPage.getPages());
        articleDTO.setTotal((int) articleIPage.getTotal());
        return articleDTO;
    }


    private Page<Article> getPage(Map<String, Object> params){

        /**
         * 当前页码
         */
        int pageNo = 1;
        /**
         * 每页条数
         */
        int pageSize = 10;


        //分页参数
        if (params.get("pageNo") != null) {
            pageNo = Integer.parseInt((String) params.get("pageNo"));
        }
        if (params.get("pageSize") != null) {
            pageSize = Integer.parseInt((String) params.get("pageSize"));
        }

        return  new Page<Article>(pageNo, pageSize,true);

    }

    @Override
    public JSONArray getFormatArticleList(List<Article> list) {
        JSONArray array = new JSONArray();
        for (Article article :list) {
            JSONObject object = new JSONObject();
            object.put("articleId", article.getArticleId());
            object.put("userName", article.getUserName());
            object.put("userLink", article.getUserLink());
            object.put("title", article.getTitle());
            object.put("summary", article.getSummary());
            object.put("weight", article.getWeight());
            object.put("tags", article.getTagArray());
            object.put("createTime", article.getCreateTime());
            object.put("viewNum", article.getViewNum());
            array.add(object);
        }
        return array;
    }


    @Override
    public List<ArticleArchivesDTO> queryArticleArchives(int limit) {
        return this.baseMapper.queryArticleArchives(limit);
    }



    /**
     * 根据id获取文章，添加阅读数
     *
     * @param articleId
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public JSONObject getArticleDetailAndAddViewNum(Integer articleId) {
        Article article = this.getById(articleId);
        if (article == null) {
            throw new RestException("该文章不存在");
        }

        JSONObject object = new JSONObject();
        // 1、文章详情
        object.put("id", article.getArticleId());
        object.put("title", article.getTitle());
        object.put("summary", article.getSummary());
        object.put("createTime", article.getCreateTime());

        article.setViewNum(article.getViewNum() + 1);
        object.put("viewNum", article.getViewNum());
        object.put("content", article.getContent());
        object.put("contentHtml", article.getContentHtml());
        // 2、文章作者信息
        object.put("userName",article.getUserName());
        object.put("userLink",article.getUserLink());


        // 3、文章所属分类信息
        Category categoryEntity = categoryService.getById(article.getCategoryId());
        object.put("category", categoryEntity);

        // 4、文章所属标签信息
        List<Tag> tagEntities = articleTagService.queryArticleTags(article.getArticleId());
        object.put("tags", tagEntities);

        // 5、文章阅读数 + 1
        this.updateById(article);

        return object;
    }

    /**
     * 添加一篇新文章
     *
     * @param json
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer addOneArticle(JSONObject json) {
        Map<Integer, String> maps = getTagMapInfos();

        // 1、保存文章信息
        Article article = new Article();
        article.setViewNum(0);
        article.setCreateTime(new Date());
        article.setUpdateTime(new Date());
        article.setWeight(0);
        article.setTitle(json.getString("title"));
        article.setSummary(json.getString("summary"));
        article.setUserName(json.getString("userName"));
        article.setUserLink(json.getString("userLink"));

        JSONObject body = json.getJSONObject("body");
        article.setContent(body.getString("content"));
        article.setContentHtml(body.getString("contentHtml"));

        // 2、设置分类信息
        JSONObject category = json.getJSONObject("category");
        article.setCategoryId(category.getInteger("categoryId"));

        // 3、设置标签信息
        JSONArray tags = json.getJSONArray("tags");
        StringBuilder tagStr = new StringBuilder();
        for (int i = 0; i < tags.size(); i++ ) {
            Integer tagId = tags.getJSONObject(i).getInteger("tagId");
            if (i != 0){
                tagStr.append(",");
            }
            tagStr.append(maps.get(tagId));
        }
        article.setTags(tagStr.toString());

        // 4、插入文章信息
        this.save(article);

        Integer id = article.getArticleId();

        // 5、插入文章对应的标签信息
        List<ArticleTag> articleTagEntityList = new ArrayList<>();
        for (int i = 0; i < tags.size(); i++ ) {
            Integer tagId = tags.getJSONObject(i).getInteger("tagId");
            ArticleTag articleTagEntity = new ArticleTag();
            articleTagEntity.setArticleId(id);
            articleTagEntity.setTagId(tagId);
            articleTagEntity.setCreateTime(new Date());
            articleTagEntity.setUpdateTime(new Date());
            articleTagEntityList.add(articleTagEntity);
        }
        articleTagService.saveBatch(articleTagEntityList);

        return id;
    }

    /**
     * 获取标签Map数据
     *
     * @return
     */
    private  Map<Integer, String> getTagMapInfos() {
        List<Tag> tagEntities = tagService.list();
        Map<Integer, String> map = new HashMap<>();
        for (Tag tag: tagEntities){
            map.put(tag.getTagId(), tag.getTagName());
        }
        return map;
    }

    /**
     * 更新一篇文章
     *
     * @param article
     * @param json
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer updateOneArticle(Article article, JSONObject json) {
        Integer id = article.getArticleId();
        Map<Integer, String> map = getTagMapInfos();

        // 1、更新文章信息
        article.setTitle(json.getString("title"));
        article.setSummary(json.getString("summary"));
        article.setUserName(json.getString("userName"));
        article.setUserLink(json.getString("userLink"));

        JSONObject body = json.getJSONObject("body");
        article.setContent(body.getString("content"));
        article.setContentHtml(body.getString("contentHtml"));

        JSONObject category = json.getJSONObject("category");
        if (article.getCategoryId().intValue() != category.getInteger("category_id").intValue()) {
            article.setCategoryId(category.getInteger("category_id"));
        }

        // 2、获取文章对应的标签信息
        List<ArticleTag> articleTagEntityList = new ArrayList<>();
        StringBuilder tagStr = new StringBuilder();
        JSONArray tags = json.getJSONArray("tags");
        for (int i = 0; i < tags.size(); i++ ) {
            Integer tagId = tags.getJSONObject(i).getInteger("tag_id");
            if (i != 0){
                tagStr.append(",");
            }
            tagStr.append(map.get(tagId));

            ArticleTag articleTagEntity = new ArticleTag();
            articleTagEntity.setArticleId(id);
            articleTagEntity.setTagId(tagId);
            articleTagEntity.setCreateTime(new Date());
            articleTagEntity.setUpdateTime(new Date());
            articleTagEntityList.add(articleTagEntity);
        }
        article.setTags(tagStr.toString());

        article.setUpdateTime(new Date());

        this.updateById(article);

        // 3、删除之前的文章标签信息
        QueryWrapper<ArticleTag> entityWrapper = new QueryWrapper<>();

        entityWrapper.eq("article_id", id);

        articleTagService.remove(entityWrapper);

        // 4、更新文章标签信息
        articleTagService.saveBatch(articleTagEntityList);

        return id;
    }




}
