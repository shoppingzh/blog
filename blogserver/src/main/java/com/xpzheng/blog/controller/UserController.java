package com.xpzheng.blog.controller;

import com.xpzheng.blog.controller.util.AjaxResult;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("user")
public class UserController {

    private static final Logger LOG = LoggerFactory.getLogger(UserController.class);

//    @Autowired
//    private UserService userService;

    @PostMapping("login")
    public AjaxResult login(@RequestParam(required = false) String username,
                            @RequestParam(required = false) String password){
//        User user = userService.getByUsername(username);
        LOG.debug("登录名：" + username);
        Map<String, Object> vo = new HashMap<String, Object>();
        vo.put("token", "admin-token");
        return AjaxResult.success(vo);
    }

    @GetMapping("info")
    public AjaxResult info(){
        Map<String, Object> vo = new HashMap<String, Object>();
        List<String> roles=  new ArrayList<String>();
        roles.add("admin");
        vo.put("roles", roles);
        vo.put("introduction", "super");
        vo.put("name", "超级管理员");
        return AjaxResult.success(vo);
    }

}
