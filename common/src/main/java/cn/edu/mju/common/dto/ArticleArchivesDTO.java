package cn.edu.mju.common.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author <a href="https://github.com/haidilaohotpot">cheng</a>
 * @since 1.0.0 2019/9/27
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArticleArchivesDTO {
    private String year;
    private String month;
    private Integer count;
}
