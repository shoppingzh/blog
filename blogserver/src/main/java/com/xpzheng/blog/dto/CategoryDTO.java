/**
 * 
 */
package com.xpzheng.blog.dto;

import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author xpzheng
 *
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class CategoryDTO extends BaseDTO {

    private String name;
    private CategoryDTO parent;
    private List<CategoryDTO> children;

}
