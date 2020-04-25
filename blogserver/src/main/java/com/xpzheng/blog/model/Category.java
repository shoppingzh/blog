package com.xpzheng.blog.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class Category extends BaseModel {

    private String name;
    private Long pid;
    private String path;

}
