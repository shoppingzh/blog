package com.xpzheng.blog.controller;

import com.xpzheng.blog.controller.data.AjaxResult;
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
    public AjaxResult page(@RequestParam(defaultValue = DEFAULT_PAGE) 
    Integer page, @RequestParam(defaultValue = DEFAULT_PAGESIZE) Integer pageSize, String keyword) {
        TagDTO tagDTO = new TagDTO();
        tagDTO.setKeyword(keyword);
        return success(tagService.page(page, pageSize, tagDTO));
    }

    @PostMapping
    public AjaxResult add(TagDTO tag) {
        Long id = tagService.add(tag);
        return id != null ? success(id) : failed();
    }

    @PutMapping("/{id}")
    public AjaxResult update(TagDTO tag) {
        return success(tagService.update(tag));
    }

    @DeleteMapping("/{id}")
    public AjaxResult remove(@PathVariable("id") Long id) {
        return success(tagService.remove(id));
    }

}
