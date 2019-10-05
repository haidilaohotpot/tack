package cn.edu.mju.manager.service;

import cn.edu.mju.common.entity.Menu;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * @author <a href="https://github.com/haidilaohotpot">wonder4work</a>
 * @since 1.0.0 2019/9/26
 * @see cn.edu.mju.manager.service.serviceImpl.MenuServiceImpl
 */
public interface MenuService extends IService<Menu> {

    /**
     * 查询所有菜单并根据order排序
     * @return
     */
    List<Menu> list(Map<String,Object> params);


}
