package cn.edu.mju.api.controller;

import cn.edu.mju.api.enums.ResultEnum;
import cn.edu.mju.api.vo.ResultMap;
import cn.edu.mju.common.dto.ArticleArchivesDTO;
import cn.edu.mju.common.dto.ArticleDTO;
import cn.edu.mju.common.dto.TagPageDTO;
import cn.edu.mju.common.entity.Article;
import cn.edu.mju.common.exception.RestException;
import cn.edu.mju.manager.service.ArticleService;
import cn.edu.mju.manager.service.ArticleTagService;
import cn.edu.mju.manager.service.CategoryService;
import cn.edu.mju.manager.service.TagService;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author <a href="https://github.com/haidilaohotpot">cheng</a>
 * @since 1.0.0 2019/9/26
 */

@Slf4j
@RestController
@RequestMapping("/article")
public class ArticleController {

    //热门或新文章的查询个数
    public static final int HOT_OR_NEW_ARTICLE_NUM = 6;

    public static final int ARTICLE_ARCHIVE_LIMIT_NUM = 8;

    @Autowired
    private ArticleService articleService;

    @Autowired
    private ArticleTagService articleTagService;

    @Autowired
    private TagService tagService;

    @Autowired
    private CategoryService categoryService;

    /**
     * 文章列表 分页查询
     * params: pageNo  pageSize  categoryId  year  month
     */
    @GetMapping("/list")
    public ResultMap list(@RequestParam Map<String, Object> params){


        String tagIdStr = (String) params.get("tagId");
        //如果tagId不为空 根据标签id查询文章
        if (StringUtils.isNotBlank(tagIdStr)) {
            Integer tagId = Integer.parseInt(tagIdStr);
            TagPageDTO tagPageDTO = getTagPageDTO(params, tagId);
            List<Article> articleList = articleTagService.queryArticlesByTag(tagPageDTO);
            JSONArray array = articleService.getFormatArticleList(articleList);
            Map<String,Object> page = new HashMap<>();

            //表示有数据
            page.put("hasPage", true);
            if(null==articleList||articleList.size()<tagPageDTO.getPageSize()){

                //表示无数据
                page.put("hasPage",false);
                return ResultMap.ok(array,page,ResultEnum.SUCCESS);

            }
            return ResultMap.ok(array,page,ResultEnum.SUCCESS);
        }

        ArticleDTO articleDTO = articleService.queryPage(params);

        List<Article> list = (List<Article>) articleDTO.getData();

        Map<String,Object> page = new HashMap<>();


        page.put("current", articleDTO.getCurrent());
        page.put("pages", articleDTO.getPages());
        page.put("total", articleDTO.getTotal());

        JSONArray array = articleService.getFormatArticleList(list);
        return ResultMap.ok(array,page,ResultEnum.SUCCESS);
    }

    /**
     * 构造分页参数
     *
     * @param params
     * @param tagId
     * @return
     */
    private TagPageDTO getTagPageDTO(Map<String, Object> params, Integer tagId) {
        Integer pageNo = 1;
        Integer pageSize = 10;
        //分页参数
        if (params.get("pageNo") != null) {
            pageNo = Integer.parseInt((String) params.get("pageNo"));
        }
        if (params.get("pageSize") != null) {
            pageSize = Integer.parseInt((String) params.get("pageSize"));
        }

        Integer offset = (pageNo - 1) * pageSize;
        TagPageDTO tagPageDTO = new TagPageDTO(offset, pageSize, tagId);
        return tagPageDTO;
    }

    /**
     * 最热文章
     */
    @GetMapping("/hot")
    public ResultMap listHotArticles() {
        JSONArray array = getHotOrNewArticles("view_num");
        return ResultMap.ok(array,ResultEnum.SUCCESS);
    }

    /**
     * 最新文章
     */
    @GetMapping("/new")
    public ResultMap listNewArticles() {
        JSONArray array = getHotOrNewArticles("create_time");
        return ResultMap.ok(array,ResultEnum.SUCCESS);
    }

    /**
     * 获取最热或最新文章
     * type 可取 view_num 最热
     * create_time 最新
     *
     * @param type
     * @return
     */
    private JSONArray getHotOrNewArticles(String type) {
        Page<Article> page = new Page<>(1, HOT_OR_NEW_ARTICLE_NUM);

        QueryWrapper<Article> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByAsc(false, type);

        Page<Article> pageList = (Page<Article>) articleService.page(page, queryWrapper);

        JSONArray array = new JSONArray();
        for (Article article : pageList.getRecords()) {
            JSONObject object = new JSONObject();
            object.put("id", article.getArticleId());
            object.put("title", article.getTitle());
            array.add(object);
        }
        return array;
    }

    /**
     * 汇总查询
     */
    @GetMapping("/archives")
    public ResultMap archives() {
        List<ArticleArchivesDTO> archivesDTOList = articleService.queryArticleArchives(ARTICLE_ARCHIVE_LIMIT_NUM);
        return ResultMap.ok(archivesDTOList,ResultEnum.SUCCESS);
    }

    /**
     * 查看文章详情时：
     * 获取文章详情
     * 包含作者信息
     * <p>
     * 同时要增加文章阅读数
     */
    @GetMapping("/view/{id}")
    public ResultMap oneArticleInfo(@PathVariable("id") Integer id) {
        JSONObject detailAndAddViewNum = articleService.getArticleDetailAndAddViewNum(id);
        return ResultMap.ok(detailAndAddViewNum,ResultEnum.SUCCESS);
    }


    /**
     * 编辑文章时：
     * 通过文章Id获取文章详情
     * 不需要用户信息
     */
    @GetMapping("/{id}")
    public ResultMap getArticleById(@PathVariable("id") Long id) {
        Article article = articleService.getById(id);
        JSONObject object = new JSONObject();
        object.put("article_id", article.getArticleId());
        object.put("title", article.getTitle());
        object.put("summary", article.getSummary());
        object.put("content", article.getContent());
        object.put("contentHtml", article.getContentHtml());
        object.put("userName", article.getUserName());
        object.put("userLink", article.getUserLink());

        object.put("category", categoryService.getById(article.getCategoryId()));
        object.put("tags", articleTagService.queryArticleTags(article.getArticleId()));

        return ResultMap.ok(object,ResultEnum.SUCCESS);
    }

    /**
     * 文章编辑与新增
     */
    @PostMapping("/publish")
    public ResultMap save(@RequestBody JSONObject json) {

        Integer id = json.getInteger("id");
        if (id != null) {
            //编辑文章
            Article article = articleService.getById(id);
            if (article == null) {
                throw new RestException("参数错误");
            }
            articleService.updateOneArticle(article, json);
        } else {
            //新增文章
            id = articleService.addOneArticle(json);
        }

        JSONObject object = new JSONObject();
        object.put("articleId", id);
        return ResultMap.ok(object,ResultEnum.SUCCESS);
    }


}

