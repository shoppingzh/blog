/**
 * 
 */
package com.xpzheng.blog.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author xpzheng
 *
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class TagDTO extends BaseDTO {
    
    private String name;
    private String theme;
    
    private String keyword;

}
