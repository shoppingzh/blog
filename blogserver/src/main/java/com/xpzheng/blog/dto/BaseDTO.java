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
@EqualsAndHashCode(of = "id")
public class BaseDTO {

    private String id;
    
}
