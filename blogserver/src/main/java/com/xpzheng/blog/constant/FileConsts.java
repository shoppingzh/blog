/**
 * 
 */
package com.xpzheng.blog.constant;

/**
 * @author xpzheng
 *
 */
public interface FileConsts {

    /** 本地 */
    short FILE_FROM_LOCAL = 0;
    /** 七牛云 */
    short FILE_FROM_QINIU = 1;
    /** 又拍云 */
    short FILE_FROM_YOUPAI = 2;
    
    /** 本地文件默认存储目录 */
    String DEFAULT_LOCAL_FILE_PATH = "/upload";
    
    /** 未知媒体类型 */
    short MEDIA_TYPE_NONE = 0;
    /** 图片 */
    short MEDIA_TYPE_IMAGE = 1;
    /** 视频 */
    short MEDIA_TYPE_VIDEO = 2;
    /** 音频 */
    short MEDIA_TYPE_AUDIO = 3;
}
