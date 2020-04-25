package com.xpzheng.blog.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = false)
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
