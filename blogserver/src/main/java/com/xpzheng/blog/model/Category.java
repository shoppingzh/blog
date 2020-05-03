package com.xpzheng.blog.model;

import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@TableName(value = "t_category")
public class Category extends BaseModel {

    private String name;
    private Long pid;
    private String path;

    public Category() {
        super();
    }

    public Category(Long id) {
        super(id);
    }

}
