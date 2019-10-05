package cn.edu.mju.common.dto;

import lombok.Data;

/**
 * @author <a href="https://github.com/haidilaohotpot">cheng</a>
 * @since 1.0.0 2019/9/29
 */
@Data
public class ArticleDTO {

    private int current;

    private int pages;

    private int total;

    private Object data;



}
