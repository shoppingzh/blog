package com.xpzheng.blog.model;

import lombok.Data;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableField;

@Data
public abstract class BaseModel {

    @TableField(value = "gmt_create")
    protected Date gmtCreate;
    @TableField(value = "gmt_modify")
    protected Date gmtModify;
    protected Boolean deleted;

    public BaseModel() {
        super();
    }

}
