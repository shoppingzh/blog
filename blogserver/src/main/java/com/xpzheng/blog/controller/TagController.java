package com.xpzheng.blog.controller;

import com.xpzheng.blog.controller.util.AjaxResult;
import com.xpzheng.blog.dto.TagDTO;
import com.xpzheng.blog.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "tag")
public class TagController extends BaseController {

    @Autowired
    private TagService tagService;

    @GetMapping
    public AjaxResult list(Integer page, @RequestParam(defaultValue = DEFAULT_PAGESIZE) Integer pageSize,
            @RequestParam(required = false) TagDTO tag) {
        return success(tagService.page(page, pageSize, tag));
    }

    @PostMapping
    public AjaxResult add(TagDTO tag) {
        return success(tagService.add(tag));
    }

    @PutMapping("/{id}")
    public AjaxResult update(TagDTO tag) {
        System.out.println(tag);
        return success(tagService.update(tag));
    }

    @DeleteMapping("/{id}")
    public AjaxResult remove(@PathVariable("id") Long id) {
        return success(tagService.remove(id));
    }

}
