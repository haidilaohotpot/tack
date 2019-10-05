package cn.edu.mju.manager.service;

import cn.edu.mju.common.dto.TagPageDTO;
import cn.edu.mju.common.entity.Article;
import cn.edu.mju.common.entity.ArticleTag;
import cn.edu.mju.common.entity.Tag;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * 文章与标签
 * @author <a href="https://github.com/haidilaohotpot">wonder4work</a>
 * @since 1.0.0 2019/9/26
 */
public interface ArticleTagService extends IService<ArticleTag> {

    /**
     * 查询最热标签
     *
     * @param limit 一次查询多少个
     * @return
     */
    List<Integer> queryHotTagIds(Integer limit);

    /**
     * 根据标签查询文章
     *
     * @param tagPageDTO
     * @return
     */
    List<Article> queryArticlesByTag(TagPageDTO tagPageDTO);

    /**
     * 根据文章id
     * 获取文章所有标签
     *
     * @param articleId
     * @return
     */
    List<Tag> queryArticleTags(Integer articleId);

}
