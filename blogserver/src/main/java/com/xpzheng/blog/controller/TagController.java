package com.xpzheng.blog.controller;

import com.xpzheng.blog.controller.util.AjaxResult;
import com.xpzheng.blog.model.Tag;
import com.xpzheng.blog.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "tag")
public class TagController extends BaseController {

    @Autowired
    private TagService tagService;

    @GetMapping()
    public AjaxResult list(Integer page, Integer pageSize) {
        return success(tagService.list(page, pageSize));
    }

    @PostMapping()
    public AjaxResult add(Tag tag) {
        tagService.add(tag);
        return success();
    }

    public AjaxResult update(Tag tag) {
        return success();
    }

    @DeleteMapping("/{id}")
    public AjaxResult remove(@PathVariable("id") Long id) {
        tagService.remove(id);
        return success();
    }

}
