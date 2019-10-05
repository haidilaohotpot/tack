package cn.edu.mju.manager.service;

import cn.edu.mju.common.dto.ArticleArchivesDTO;
import cn.edu.mju.common.dto.ArticleDTO;
import cn.edu.mju.common.entity.Article;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * @author <a href="https://github.com/haidilaohotpot">wonder4work</a>
 * @since 1.0.0 2019/9/26
 * @see cn.edu.mju.manager.service.serviceImpl.ArticleServiceImpl
 */
public interface ArticleService extends IService<Article> {


    ArticleDTO queryPage(Map<String, Object> params);


    JSONArray getFormatArticleList(List<Article> list);


    /**
     * 发布文章按年-月汇总
     *
     * @param limit
     * @return
     */
    List<ArticleArchivesDTO> queryArticleArchives(int limit);

    /**
     * 根据id获取文章，添加阅读数
     *
     * @param articleId
     * @return
     */
    JSONObject getArticleDetailAndAddViewNum(Integer articleId);

    /**
     * 添加一篇新文章
     *
     * @param json
     * @return
     */
    Integer  addOneArticle(JSONObject json);

    /**
     * 更新一篇文章
     *
     * @param article
     * @param json
     * @return
     */
    Integer updateOneArticle(Article article, JSONObject json);


}
