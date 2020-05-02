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
    private Long id;
    @TableField(value = "gmt_create")
    private Date gmtCreate;
    @TableField(value = "gmt_modify")
    private Date gmtModify;
    private Boolean deleted;

}
