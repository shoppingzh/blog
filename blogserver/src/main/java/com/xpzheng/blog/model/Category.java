package com.xpzheng.blog.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false, of = "id")
@TableName(value = "t_category")
public class Category extends BaseModel {

    @TableId(type = IdType.AUTO)
    protected Long id;
    private String name;
    private Long pid;
    private String path;

    public Category() {
        super();
    }

    public Category(Long id) {
        this.id = id;
    }

}
