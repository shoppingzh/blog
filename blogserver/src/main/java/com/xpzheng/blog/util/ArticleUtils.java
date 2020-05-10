/**
 * 
 */
package com.xpzheng.blog.util;

import java.io.UnsupportedEncodingException;

import org.apache.commons.lang3.StringUtils;

/**
 * 文章工具类
 * @author xpzheng
 *
 */
public class ArticleUtils {

    private static final String DEFAULT_CHARSET = "UTF-8";
    public static final int DEFAULT_SUMMARY_WORDS = 200;

    /**
     * 截取内容获得{@link #DEFAULT_SUMMARY_WORDS}字的摘要
     * @param content       内容
     * @return
     */
    public static String createSummary(String content) {
        return createSummary(content, DEFAULT_SUMMARY_WORDS);
    }

    /**
     * 截取内容获得摘要
     * @param content       内容
     * @param words         字数
     * @return
     */
    public static String createSummary(String content, int words) {
        if (StringUtils.isBlank(content)) {
            return content;
        }
        int bytes = words * 3;
        try {
            if (content.getBytes(DEFAULT_CHARSET).length <= bytes) {
                return content;
            }
            String subContent = content.substring(0, words);
            for (int i = words; subContent.getBytes(DEFAULT_CHARSET).length < bytes; i++) {
                if (i >= content.length()) {
                    break;
                }
                subContent += content.charAt(i);
            }
            return subContent;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return content;
    }

}
