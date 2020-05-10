/**
 * 
 */
package com.xpzheng.blog.util;

import java.util.Calendar;
import java.util.Date;

/**
 * 日期工具类
 * 
 * @author xpzheng
 *
 */
public class DateUtils {

    /**
     * 获取某天的开始时刻
     * 
     * @param day  天
     * @return
     */
    public static Date getBeginTime(Date day) {
        if (day == null) {
            return null;
        }
        Calendar c = Calendar.getInstance();
        c.setTime(day);
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);
        return c.getTime();
    }

    /**
     * 获取一天的结束时刻
     * @param day  天
     * @return
     */
    public static Date getEndTime(Date day) {
        if (day == null) {
            return null;
        }
        Calendar c = Calendar.getInstance();
        c.setTime(day);
        c.set(Calendar.HOUR, 23);
        c.set(Calendar.MINUTE, 59);
        c.set(Calendar.SECOND, 59);
        c.set(Calendar.MILLISECOND, 999);
        return c.getTime();
    }

    /**
     * 获取某个月的第一天
     * @param month  月份
     * @return
     */
    public static Date getFirstDay(Date month) {
        if (month == null) {
            month = new Date();
        }
        Calendar c = Calendar.getInstance();
        c.setTime(month);
        c.set(Calendar.DAY_OF_MONTH, c.getActualMinimum(Calendar.DAY_OF_MONTH));
        return getBeginTime(c.getTime());
    }

    /**
     * 获取某个月的最后一天
     * @param month  月份
     * @return
     */
    public static Date getLastDay(Date month) {
        if (month == null) {
            month = new Date();
        }
        Calendar c = Calendar.getInstance();
        c.setTime(month);
        c.set(Calendar.DAY_OF_MONTH, c.getActualMaximum(Calendar.DAY_OF_MONTH));
        return getBeginTime(c.getTime());
    }

}
