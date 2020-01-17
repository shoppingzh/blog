package com.xpzheng.blog.mapper;

import java.io.Serializable;
import java.util.List;

public interface BaseMapper<T> {

    /**
     * 插入
     * @param model
     * @return
     */
    Serializable insert(T model);

    /**
     * 更新
     * @param model
     * @return
     */
    boolean update(T model);

    /**
     * 删除
     * @param id
     * @return
     */
    boolean deleteById(Serializable id);

    /**
     * 通过ID检索对象
     * @param id
     * @return
     */
    T selectById(Serializable id);

    /**
     * 通过通用查询条件检索列表
     * @param model
     * @return
     */
    List<T> select(T model);

}
