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
public class FileDTO extends BaseDTO {
    
    private short from;
    private String filename;
    private String filepath;
    private long filesize;
    private String contentType;
    private short mediaType;
    // 引用路径
    private String refPath;

}
