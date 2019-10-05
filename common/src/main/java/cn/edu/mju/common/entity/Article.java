package cn.edu.mju.common.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.ToStringSerializer;
import com.alibaba.fastjson.support.spring.annotation.ResponseJSONP;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 文章实体类
 * @author <a href="https://github.com/haidilaohotpot">cheng</a>
 * @since 1.0.0 2019/9/26
 */
@Data
@TableName("t_article")
public class Article implements Serializable {

    /**
     * 主键ID
     */
    @TableId
    private Integer articleId;


    /**
     * 作者昵称
     */
    private String userName;


    /**
     * 用户链接
     */

    private String userLink;

    /**
     * 文章标题
     */
    private String title;
    /**
     * 文章摘要
     */
    private String summary;
    /**
     * 文章内容txt
     */
    private String content;
    /**
     * 文章内容html
     */
    private String contentHtml;
    /**
     * 浏览数
     */
    private Integer viewNum;

    /**
     * 权重
     */
    private Integer weight;
    /**
     * 文章标签 逗号分隔
     */
    private String tags;
    /**
     * 文章分类ID
     */
    private Integer categoryId;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 更新时间
     */
    private Date updateTime;


    public String[] getTagArray() {
        return tags!= null ? tags.split(",") : null;
    }

}
