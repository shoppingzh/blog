/**
 * 
 */
package com.xpzheng.blog.mapper;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xpzheng.blog.model.Article;
import com.xpzheng.blog.model.Stat;

/**
 * @author xpzheng
 *
 */
@Repository
public interface ArticleMapper extends BaseMapper<Article> {

    /**
     * 按照天统计每日文章数目
     * @return
     */
    List<Stat<Date>> statDay(@Param("params") Map<String, Object> params);

}
