/**
 * 
 */
package com.xpzheng.blog.dto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;

import com.baomidou.mybatisplus.core.metadata.IPage;

import lombok.Data;
import lombok.ToString;

/**
 * @author xpzheng
 *
 */
@Data
@ToString
public class PageDTO<E> {

    private int page = 1;
    private int pageSize = 10;
    private long total;
    private List<E> data = Collections.emptyList();
    
    public int getPages() {
        if (pageSize <= 0) {
            return 0;
        }
        return (int) Math.ceil(1.0f * total / pageSize);
    }

    /**
     * 将mp的Page转换为业务层Page
     * @param aPage      mp的Page对象
     * @param convert    DTO对象的转换规则
     * @return
     */
    public static <T, E> PageDTO<E> valueOf(IPage<T> aPage, Function<T, E> convert) {
        PageDTO<E> page = new PageDTO<E>();
        if (aPage != null) {
            page.setPage((int) aPage.getCurrent());
            page.setPageSize((int) aPage.getSize());
            page.setTotal(aPage.getTotal());
            List<E> data = new ArrayList<E>();
            aPage.getRecords().forEach((x) -> {
                E e = convert.apply(x);
                data.add(e);
            });
            page.setData(data);
        }
        return page;
    }

}
