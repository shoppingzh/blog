package com.xpzheng.blog.model;

import lombok.Data;

import java.util.Date;

@Data
public class User {
    private String id;
    private String username;
    private String password;
    private String nickname;
    private Integer age;
    private Boolean deleted;
    private Date gmtCreate;
    private Date gmtModify;
}
