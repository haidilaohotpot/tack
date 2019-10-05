package cn.edu.mju.common.dto;

import cn.edu.mju.common.entity.Tag;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author <a href="https://github.com/haidilaohotpot">cheng</a>
 * @since 1.0.0 2019/9/26
 */
@Data
public class TagDTO  {

    //此标签包含的文章数
    private int articles;

    /**
     * 主键ID
     */
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
