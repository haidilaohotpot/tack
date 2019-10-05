package cn.edu.mju.common.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.ToStringSerializer;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 文章标签关联实体类
 * @author <a href="https://github.com/haidilaohotpot">cheng</a>
 * @since 1.0.0 2019/9/26
 */
@Data
@TableName("t_article_tag")

public class ArticleTag implements Serializable {

    /**
     * 主键ID
     */
    @TableId
    private Integer id;
    /**
     * 文章ID
     */
    private Integer articleId;
    /**
     * 标签ID
     */
    private Integer tagId;

    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 更新时间
     */
    private Date updateTime;

}
