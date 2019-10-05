package cn.edu.mju.manager.mapper;

import cn.edu.mju.common.dto.TagPageDTO;
import cn.edu.mju.common.entity.Article;
import cn.edu.mju.common.entity.ArticleTag;
import cn.edu.mju.common.entity.Tag;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author <a href="https://github.com/haidilaohotpot">wonder4work</a>
 * @since 1.0.0 2019/9/26
 */
@Mapper
public interface ArticleTagMapper extends BaseMapper<ArticleTag> {

    /**
     * 查询最热标签
     *
     * @param limit
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
     * 获取文章所有标签
     *
     * @param articleId
     * @return
     */
    List<Tag> queryArticleTags(Integer articleId);

}
