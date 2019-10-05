package cn.edu.mju.common.dto;

import cn.edu.mju.common.entity.Category;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author <a href="https://github.com/haidilaohotpot">cheng</a>
 * @since 1.0.0 2019/9/26
 */
@Data
public class CategoryDTO {

    private int articles;

    /**
     * 主键ID
     */
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
