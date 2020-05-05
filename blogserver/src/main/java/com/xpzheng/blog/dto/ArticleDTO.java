/**
 * 
 */
package com.xpzheng.blog.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author xpzheng
 *
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class ArticleDTO extends BaseDTO {

    private String title;
    private String summary;
    private String content;
    private String plainContent;
    private boolean draft;
    private Date createTime;
    private Date modifyTime;
    private List<TagDTO> tags = new ArrayList<TagDTO>();
    private CategoryDTO category;
    private FileDTO thumbnail;

}
