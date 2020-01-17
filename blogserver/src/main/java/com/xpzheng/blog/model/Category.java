package com.xpzheng.blog.model;

import lombok.Data;

@Data
public class Category extends BaseModel {

    private String name;
    private Long pid;
    private String path;

}
