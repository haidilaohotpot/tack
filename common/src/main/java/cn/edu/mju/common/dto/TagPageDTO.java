package cn.edu.mju.common.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author <a href="https://github.com/haidilaohotpot">cheng</a>
 * @since 1.0.0 2019/9/26
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TagPageDTO {

    Integer offset;//开始位置
    Integer pageSize;//查询多少
    Integer tagId;//标签id


}
