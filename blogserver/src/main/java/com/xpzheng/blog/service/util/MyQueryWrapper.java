/**
 * 
 */
package com.xpzheng.blog.service.util;

import java.io.Serializable;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

/**
 * @author xpzheng
 *
 */
public class MyQueryWrapper<T> extends QueryWrapper<T> {

    private static final long serialVersionUID = -8147792709424445076L;
    private static final String FIELD_DELETE = "deleted";
    private static final String FIELD_ID = "id";

    public MyQueryWrapper() {
        super();
    }

    public MyQueryWrapper(T entity, String... columns) {
        super(entity, columns);
    }

    public MyQueryWrapper(T entity) {
        super(entity);
    }

    public MyQueryWrapper(boolean deleted) {
        super();
        if (deleted) {
            isDeleted();
        } else {
            isNotDelete();
        }
    }

    /**
     * 没有被软删除
     * @return
     */
    public MyQueryWrapper<T> isNotDelete() {
        eq(FIELD_DELETE, false);
        return this;
    }

    /**
     * 已被软删除
     * @return
     */
    public MyQueryWrapper<T> isDeleted() {
        eq(FIELD_DELETE, true);
        return this;
    }
    
    /**
     * id匹配
     * @param id
     * @return
     */
    public MyQueryWrapper<T> id(Serializable id) {
        eq(FIELD_ID, id);
        return this;
    }

}
