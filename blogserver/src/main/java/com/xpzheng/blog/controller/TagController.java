package com.xpzheng.blog.controller;

import com.xpzheng.blog.controller.util.AjaxResult;
import com.xpzheng.blog.model.Tag;
import com.xpzheng.blog.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "tag")
public class TagController {

    @Autowired
    private TagService tagService;

    @GetMapping("")
    public AjaxResult list(Integer page, Integer pageSize) {
        return AjaxResult.success(page == null ? tagService.list(null) : tagService.page(null, page, pageSize == null ? 15 : pageSize));
    }

    @GetMapping("/{id}")
    public Tag getOne(@PathVariable("id") Long id) {
        return tagService.getById(id);
    }

}
