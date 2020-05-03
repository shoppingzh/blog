package com.xpzheng.blog.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;

@Data
@EqualsAndHashCode(of = { "id" })
public class BaseModel {

    @TableId(type = IdType.AUTO)
    protected Long id;
    @TableField(value = "gmt_create")
    protected Date gmtCreate;
    @TableField(value = "gmt_modify")
    protected Date gmtModify;
    protected Boolean deleted;

    public BaseModel() {
        super();
    }

    public BaseModel(Long id) {
        super();
        this.id = id;
    }

}
