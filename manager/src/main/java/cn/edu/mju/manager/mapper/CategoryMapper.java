package cn.edu.mju.manager.mapper;

import cn.edu.mju.common.dto.CategoryDTO;
import cn.edu.mju.common.entity.Category;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author <a href="https://github.com/haidilaohotpot">cheng</a>
 * @since 1.0.0 2019/9/26
 */
@Mapper
public interface CategoryMapper extends BaseMapper<Category> {

    /**
     * 文章分类详情
     *
     * @return
     */
    List<CategoryDTO> queryCategoryDetails();

    /**
     * 单条文章分类详情
     *
     * @return
     */
    CategoryDTO queryOneCategoryDetail(Integer categoryId);

}
