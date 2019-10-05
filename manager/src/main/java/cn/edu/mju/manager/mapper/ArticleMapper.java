package cn.edu.mju.manager.mapper;

import cn.edu.mju.common.dto.ArticleArchivesDTO;
import cn.edu.mju.common.entity.Article;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 文章
 * @author <a href="https://github.com/haidilaohotpot">wonder4work</a>
 * @since 1.0.0 2019/9/26
 */
@Mapper
public interface ArticleMapper extends BaseMapper<Article> {


    List<ArticleArchivesDTO> queryArticleArchives(int limit);
}
