/**
 * 
 */
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
@TableName(value = "t_file")
@EqualsAndHashCode(of = "id")
public class FileEntity {

    @TableId(type = IdType.ASSIGN_ID)
    private String id;
    private Short filefrom;
    private String filename;
    private String filepath;
    private Long filesize;
    private String contentType;

}
