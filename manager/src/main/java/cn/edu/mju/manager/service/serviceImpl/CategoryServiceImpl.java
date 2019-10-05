package cn.edu.mju.manager.service.serviceImpl;

import cn.edu.mju.common.dto.CategoryDTO;
import cn.edu.mju.common.entity.Category;
import cn.edu.mju.manager.mapper.CategoryMapper;
import cn.edu.mju.manager.service.CategoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author <a href="https://github.com/haidilaohotpot">cheng</a>
 * @since 1.0.0 2019/9/26
 */
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper,Category> implements CategoryService {


    /**
     * 文章分类详情
     *
     * @return
     */
    @Override
    public List<CategoryDTO> queryCategoryDetails() {
        return this.baseMapper.queryCategoryDetails();
    }

    /**
     * 单条文章分类详情
     *
     * @return
     */
    @Override
    public CategoryDTO queryOneCategoryDetail(Integer categoryId) {
        return this.baseMapper.queryOneCategoryDetail(categoryId);
    }


}
