package com.xpzheng.blog.model;

import lombok.Data;

@Data
public class Article extends BaseModel {

    private String uid;
    private Long cid;
    private String title;
    private String summary;
    private String content;
    private Boolean draft;

}
