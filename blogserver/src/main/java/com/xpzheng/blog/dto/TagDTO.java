/**
 * 
 */
package com.xpzheng.blog.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * @author xpzheng
 *
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ToString
public class TagDTO extends BaseDTO {
    
    private String name;
    private String theme;
    
    private String keyword;

}
