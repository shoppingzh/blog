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
    
    /** 未知媒体类型 */
    public static final short MEDIA_TYPE_NONE = 0;
    /** 图片 */
    public static final short MEDIA_TYPE_IMAGE = 1;
    /** 视频 */
    public static final short MEDIA_TYPE_VIDEO = 2;
    /** 音频 */
    public static final short MEDIA_TYPE_AUDIO = 3;
}
