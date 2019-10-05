package cn.edu.mju.common.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.ToStringSerializer;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * 日志记录表
 * @author <a href="https://github.com/haidilaohotpot">cheng</a>
 * @since 1.0.0 2019/9/27
 */
@Data
@TableName("t_log")
public class Log {

    @TableId
    private Integer logId;

    private Date createTime;

    /**
     * 记录事项
     */
    private String operation;


}
