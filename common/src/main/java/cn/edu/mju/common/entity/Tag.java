package cn.edu.mju.common.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 标签
 * @author <a href="https://github.com/haidilaohotpot">cheng</a>
 * @since 1.0.0 2019/9/26
 */
@Data
@TableName("t_tag")
public class Tag implements Serializable {

    /**
     * 主键ID
     */
    @TableId(type = IdType.AUTO)
    private Integer tagId;
    /**
     * 标签名字
     */
    private String tagName;
    /**
     * 标签图标
     */
    private String avatar;
    /**
     * 创建时间
     */
    private Date createTime;

}
