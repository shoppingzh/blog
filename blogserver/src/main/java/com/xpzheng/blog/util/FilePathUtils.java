/**
 * 
 */
package com.xpzheng.blog.util;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

/**
 * @author xpzheng
 *
 */
public class FilePathUtils {

    public static final short DATE_PATH_YEAR = 0;
    public static final short DATE_PATH_MONTH = 1;
    public static final short DATE_PATH_DAY = 2;
    public static final short DATE_PATH_HOUR = 3;
    public static final short DATE_PATH_MINUTE = 4;
    public static final short DATE_PATH_SECOND = 5;

    /**
     * 在指定目录下创建"年/月/日"类型的子目录
     * @param basePath     父目录
     * @return
     */
    public static File getDatePathFile(File basePath) {
        return getDatePathFile(basePath, DATE_PATH_DAY);
    }

    /**
     * 在指定目录下创建如"2020/02/23/11/23"这样的日期格式的子目录
     * @param basePath     父目录
     * @param type         参考{@ FilePathUtils#DATE_PATH_YEAR}等常量
     * @return
     */
    public static File getDatePathFile(File basePath, short type) {
        String datePath = getDatePath(type);
        File path = datePath == null ? basePath : new File(basePath, datePath);
        if (!path.exists()) {
            path.mkdirs();
        }
        return path;
    }

    /**
     * 获取日期(年/月/日)目录
     * @return
     */
    public static String getDatePath() {
        return getDatePath(DATE_PATH_DAY);
    }

    /**
     * 获取日期目录
     * @param type       参考{@ FilePathUtils#DATE_PATH_YEAR}等常量
     * @return
     */
    public static String getDatePath(short type) {
        List<String> formats = new ArrayList<String>();
        if (type >= DATE_PATH_YEAR) {
            formats.add("yyyy");
        }
        if (type >= DATE_PATH_MONTH) {
            formats.add("MM");
        }
        if (type >= DATE_PATH_DAY) {
            formats.add("dd");
        }
        if (type >= DATE_PATH_HOUR) {
            formats.add("HH");
        }
        if (type >= DATE_PATH_MINUTE) {
            formats.add("mm");
        }
        if (type >= DATE_PATH_SECOND) {
            formats.add("ss");
        }
        return formats.isEmpty() ? null : new SimpleDateFormat(StringUtils.join(formats, "/")).format(new Date());
    }
    
    /**
     * 拼接目录名
     * @param paths    目录
     * @return
     */
    public static String joinPath(String... paths) {
        return StringUtils.join(paths, "/").replaceAll("/{2,}", "/");
    }
    

}
