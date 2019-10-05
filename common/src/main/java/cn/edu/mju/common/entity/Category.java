package cn.edu.mju.common.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 博客分类
 * @author <a href="https://github.com/haidilaohotpot">cheng</a>
 * @since 1.0.0 2019/9/26
 */
@Data
@TableName("t_category")
public class Category implements Serializable {


    /**
     * 主键ID
     */
    @TableId(type = IdType.AUTO)
    private Integer categoryId;
    /**
     * 类别名字
     */
    private String categoryName;
    /**
     * 类别图标
     */
    private String avatar;
    /**
     * 分类描述
     */
    private String description;

    /**
     * 创建时间
     */
    private Date createTime;

}
