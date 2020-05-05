package com.xpzheng.blog.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author xpzheng
 *
 */
@Data
@EqualsAndHashCode(callSuper = false, of = "id")
@TableName(value = "t_article")
public class Article extends BaseModel {

    @TableId(type = IdType.ASSIGN_ID)
    private String id;
    private String uid;
    private Long cid;
    private String title;
    private String summary;
    private Integer hot;
    private Boolean draft;
    private String thumbnail;

}
