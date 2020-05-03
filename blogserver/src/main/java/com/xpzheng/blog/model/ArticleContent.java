/**
 * 
 */
package com.xpzheng.blog.model;

import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;

/**
 * @author xpzheng
 *
 */
@Data
@TableName(value = "t_article_content")
public class ArticleContent {

    private String aid;
    private String content;
    private String plainContent;

}
