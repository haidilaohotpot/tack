package cn.edu.mju.api.controller;

import cn.edu.mju.api.enums.ResultEnum;
import cn.edu.mju.api.vo.ResultMap;
import cn.edu.mju.common.dto.CategoryDTO;
import cn.edu.mju.common.entity.Category;
import cn.edu.mju.common.exception.RestException;
import cn.edu.mju.common.util.ValidateBeanUtils;
import cn.edu.mju.manager.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

/** 文章类别
 * @author <a href="https://github.com/haidilaohotpot">cheng</a>
 * @since 1.0.0 2019/9/26
 */
@Slf4j
@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    /**
     * 查询所有类别列表
     */
    @GetMapping("/list")
    public ResultMap list() {
        List<Category> categoryList = categoryService.list();
        log.info("categoryList = {}",categoryList.toString());
        return ResultMap.ok(categoryList,ResultEnum.SUCCESS);
    }

    /**
     * 分类详情
     */
    @GetMapping("/detail")
    public ResultMap detail() {
        List<CategoryDTO> categoryDTOList = categoryService.queryCategoryDetails();
        log.info("categoryDTOList = {}",categoryDTOList.toString());
        return ResultMap.ok(categoryDTOList,ResultEnum.SUCCESS);
    }

    /**
     * 单条分类详情
     */
    @GetMapping("/detail/{categoryId}")
    public ResultMap detailById(@PathVariable("categoryId") Integer categoryId) {
        CategoryDTO categoryDTO = categoryService.queryOneCategoryDetail(categoryId);
        log.info("categoryDTO = {}",categoryDTO.toString());
        return ResultMap.ok(categoryDTO,ResultEnum.SUCCESS);
    }


    /**
     * 保存
     */
    @PostMapping("/save")
    public ResultMap save(@RequestBody Category category) {

        if (ValidateBeanUtils.isEmpty(category)) {
            throw new RestException();
        }

        categoryService.save(category);

        return ResultMap.ok(ResultEnum.SUCCESS);
    }

    /**
     * 修改
     */
    @PostMapping("/update")
    public ResultMap update(@RequestBody Category category) {

        if (ValidateBeanUtils.isEmpty(category)) {
            throw new RestException();
        }

        //全部更新
        categoryService.updateById(category);

        return ResultMap.ok(ResultEnum.SUCCESS);
    }

    /**
     * 支持批量删除删除
     */
    @PostMapping("/delete")
    public ResultMap delete(@RequestBody Integer[] ids) {

        categoryService.removeByIds(Arrays.asList(ids));

        return ResultMap.ok(ResultEnum.SUCCESS);
    }




}
