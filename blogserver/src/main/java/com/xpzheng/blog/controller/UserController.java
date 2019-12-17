package com.xpzheng.blog.controller;

import com.xpzheng.blog.model.User;
import com.xpzheng.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping(path = "{id}")
    public @ResponseBody
    List<User> get(@PathVariable(required = false) String id){
        return userService.getAllUser();
    }


}
