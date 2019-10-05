package cn.edu.mju.manager.service.serviceImpl;

import cn.edu.mju.common.dto.TagPageDTO;
import cn.edu.mju.common.entity.Article;
import cn.edu.mju.common.entity.ArticleTag;
import cn.edu.mju.common.entity.Tag;
import cn.edu.mju.manager.mapper.ArticleMapper;
import cn.edu.mju.manager.mapper.ArticleTagMapper;
import cn.edu.mju.manager.service.ArticleTagService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author <a href="https://github.com/haidilaohotpot">cheng</a>
 * @since 1.0.0 2019/9/26
 */
@Service
public class ArticleTagServiceImpl extends ServiceImpl<ArticleTagMapper,ArticleTag> implements ArticleTagService {


    @Override
    public List<Integer> queryHotTagIds(Integer limit) {
        List<Integer> hotTagIds = this.baseMapper.queryHotTagIds(limit);
        return hotTagIds;
    }


    /**
     * 根据标签查询文章
     *
     * @param tagPageDTO
     * @return
     */
    @Override
    public List<Article> queryArticlesByTag(TagPageDTO tagPageDTO) {
        return this.baseMapper.queryArticlesByTag(tagPageDTO);
    }

    /**
     * 获取文章所有标签
     *
     * @param articleId
     * @return
     */
    @Override
    public List<Tag> queryArticleTags(Integer articleId) {
        return this.baseMapper.queryArticleTags(articleId);
    }



}
