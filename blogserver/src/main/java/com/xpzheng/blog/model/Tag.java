package com.xpzheng.blog.model;

import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@TableName("t_tag")
public class Tag extends BaseModel {

    private String name;
    private String theme;

}
