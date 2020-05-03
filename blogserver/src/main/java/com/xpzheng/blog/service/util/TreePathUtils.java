/**
 * 
 */
package com.xpzheng.blog.service.util;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

/**
 * 树型结构路径工具类
 * @author xpzheng
 *
 */
public class TreePathUtils {

    /**
     * 连接符
     */
    public static final String SEPARATER = "#";

    /**
     * 连接形成树型路径
     * @param paths
     * @return
     */
    public static String joinPath(List<?> paths) {
        if (paths == null || paths.isEmpty()) {
            return null;
        }
        String path = StringUtils.join(paths, SEPARATER);
        return alignment(path);
    }

    /**
     * 连接形成树型路径
     * @param parentPath     父路径
     * @param childPath      子路径
     * @return
     */
    public static String joinPath(String parentPath, String childPath) {
        if (StringUtils.isBlank(parentPath)) {
            return alignment(childPath);
        }
        return alignment(parentPath + childPath);
    }

    // 补齐两端
    private static String alignment(String path) {
        if (StringUtils.isBlank(path)) {
            return path;
        }
        if (!path.startsWith(SEPARATER)) {
            path = SEPARATER + path;
        }
        if (!path.endsWith(SEPARATER)) {
            path = path + SEPARATER;
        }
        return path;
    }

}
