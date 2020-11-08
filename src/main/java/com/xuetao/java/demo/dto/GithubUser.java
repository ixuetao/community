package com.xuetao.java.demo.dto;

import lombok.Data;

/**
 * @Author: Raymond * @Date: 2020/11/1 12:36 * @Description: *
 */
@Data
public class GithubUser {
    private String name;
    private long id;
    private String bio;
    private String avatar_url;
}
