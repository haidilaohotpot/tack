package cn.edu.mju.api.controller;

import cn.edu.mju.api.enums.ResultEnum;
import cn.edu.mju.api.vo.ResultMap;
import cn.edu.mju.common.entity.Menu;
import cn.edu.mju.common.exception.RestException;
import cn.edu.mju.common.util.ValidateBeanUtils;
import cn.edu.mju.manager.service.MenuService;
import com.baomidou.mybatisplus.extension.api.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * 菜单
 * @author <a href="https://github.com/haidilaohotpot">cheng</a>
 * @since 1.0.0 2019/9/27
 */
@Slf4j
@RestController
@RequestMapping("/menu")
public class MenuController {


    @Autowired
    private MenuService menuService;


    /**
     * 查询所有菜单  根据type查询
     * @return
     */
    @GetMapping("/list")
    public ResultMap list(@RequestParam Map<String,Object> params){

        List<Menu> menuList = menuService.list(params);

        return ResultMap.ok(menuList, ResultEnum.SUCCESS);

    }


    /**
     * 根据id更新
     * @param menu
     * @return
     */
    @PostMapping("/update")
    public ResultMap update(@RequestBody Menu menu){

        if(ValidateBeanUtils.isEmpty(menu) || null == menu.getMenuId()){
            throw new RestException();
        }

        menuService.updateById(menu);

        return ResultMap.ok(ResultEnum.SUCCESS);
    }



    /**
     * 保存
     */
    @RequestMapping("/save")
    public ResultMap save(@RequestBody Menu menu) {

        if(ValidateBeanUtils.isEmpty(menu)){
            throw new RestException();
        }
        menuService.save(menu);

        return ResultMap.ok(ResultEnum.SUCCESS);
    }


    /**
     * 批量化删除
     */
    @RequestMapping("/delete")
    public ResultMap delete(@RequestBody Integer[] ids) {

        menuService.removeByIds(Arrays.asList(ids));

        return ResultMap.ok(ResultEnum.SUCCESS);
    }


}
