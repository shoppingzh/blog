/**
 * 
 */
package com.xpzheng.blog.model;

import lombok.Data;

/**
 * @author xpzheng
 *
 */
@Data
public class Stat<T> {

    T group;
    Long count;

}
