/**
 * 
 */
package com.xpzheng.blog.model;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;

/**
 * @author xpzheng
 *
 */
@Data
@TableName(value = "t_article_tag")
public class ArticleTag {

    private String aid;
    private Long tid;
    @TableField(value = "gmt_create")
    private Date gmtCreate;

}
