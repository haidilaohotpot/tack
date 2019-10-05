package cn.edu.mju.manager.service;

import cn.edu.mju.common.dto.CategoryDTO;
import cn.edu.mju.common.entity.Category;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @author <a href="https://github.com/haidilaohotpot">wonder4work</a>
 * @since 1.0.0 2019/9/26
 */
public interface CategoryService extends IService<Category> {


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
