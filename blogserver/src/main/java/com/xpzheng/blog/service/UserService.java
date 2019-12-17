package com.xpzheng.blog.service;

import com.xpzheng.blog.mapper.UserMapper;
import com.xpzheng.blog.model.User;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public List<User> getAllUser(){
        System.out.println("获取所有用户");
        return userMapper.selectAll();
    }

}
