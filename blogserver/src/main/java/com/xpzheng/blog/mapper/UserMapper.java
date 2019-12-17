package com.xpzheng.blog.mapper;

import com.xpzheng.blog.model.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMapper {

    /**
     * 查询所有用户
     * @return
     */
    List<User> selectAll();

}
