package com.xpzheng.blog.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@Data
@EqualsAndHashCode(of = {"id"})
public class BaseModel {

    private Long id;
    private Date gmtCreate;
    private Date gmtModify;
    private Boolean deleted;

}
