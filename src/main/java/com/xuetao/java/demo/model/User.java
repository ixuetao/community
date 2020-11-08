package com.xuetao.java.demo.model;

import lombok.Data;

/**
 * @Author: TestRookie * @Date: 2020/11/4 7:44 * @Description: *
 */
@Data
public class User {
    private Integer id;
    private String name;
    private String accountId;
    private String token;
    private Long gmtCreate;
    private Long gmtModified;
    private String avatarUrl;

}
