package com.xpzheng.blog.controller;

import com.xpzheng.blog.controller.util.AjaxResult;
import com.xpzheng.blog.model.User;
import com.xpzheng.blog.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("user")
public class UserController {

    private static final Logger LOG = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @PostMapping("login")
    public AjaxResult login(@RequestParam(required = false) String username,
                            @RequestParam String password){
//        User user = userService.getByUsername(username);
        LOG.debug("登录名：" + username);
        return AjaxResult.success(Map.of("token", "admin-token"));
    }

    @GetMapping("info")
    public AjaxResult info(){
        return AjaxResult.success(Map.of("roles", List.of("admin"), "introduction", "I am a super administrator", "name", "超级管理员"));
    }

}
