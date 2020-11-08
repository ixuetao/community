package com.xuetao.java.demo.model;

import lombok.Data;

/**
 * @Author: TestRookie * @Date: 2020/11/7 20:05 * @Description: *
 */
@Data
public class Question {
    private Integer id;
    private String title;
    private String description;
    private Long gmt_create;
    private Long gmt_modified;
    private Integer creator;
    private String tag;
    private Integer viewCount;
    private Integer commentCount;
    private Integer likeCount;
}
