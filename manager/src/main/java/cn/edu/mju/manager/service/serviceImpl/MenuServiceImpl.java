package cn.edu.mju.manager.service.serviceImpl;

import cn.edu.mju.common.entity.Menu;
import cn.edu.mju.manager.mapper.MenuMapper;
import cn.edu.mju.manager.service.MenuService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * @author <a href="https://github.com/haidilaohotpot">cheng</a>
 * @since 1.0.0 2019/9/26
 */
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper,Menu> implements MenuService {


    @Override
    public List<Menu> list(Map<String,Object> params){

        QueryWrapper<Menu> queryWrapper = new QueryWrapper<>();

        String type = (String) params.get("type");

        if(!StringUtils.isEmpty(type)){

            //根据类型查询菜单
            Integer menuType = Integer.parseInt(type);
            queryWrapper.eq("type",menuType);

        }

        queryWrapper.orderByDesc("weight");

        return this.list(queryWrapper);

    }


}
