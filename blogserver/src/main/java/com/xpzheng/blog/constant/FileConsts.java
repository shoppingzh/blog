/**
 * 
 */
package com.xpzheng.blog.constant;

/**
 * @author xpzheng
 *
 */
public abstract class FileConsts {

    private FileConsts() {
    }

    /** 本地 */
    public static final short FILE_FROM_LOCAL = 0;
    /** 七牛云 */
    public static final short FILE_FROM_QINIU = 1;
    /** 又拍云 */
    public static final short FILE_FROM_YOUPAI = 2;
    
    /** 本地文件默认存储目录 */
    public static final String DEFAULT_LOCAL_FILE_PATH = "/upload";
}
