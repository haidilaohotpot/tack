package cn.edu.mju.common.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.ToStringSerializer;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * 菜单实体类
 * @author <a href="https://github.com/haidilaohotpot">cheng</a>
 * @since 1.0.0 2019/9/26
 */
@Data
@TableName("t_menu")
public class Menu implements Serializable {

    /**
     * 菜单id
     */
    @TableId
    private Integer menuId;

    /**
     * 名称
     */
    private String name;
    /**
     * 地址url
     */
    private String url;
    /**
     * 类型   0：目录   1：菜单   2：按钮
     */
    private int type;
    /**
     * 菜单图标地址
     */
    private String icon;
    /**
     * 权重
     */
    private int weight;


}
