/**
 * 
 */
package com.xpzheng.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xpzheng.blog.controller.util.AjaxResult;
import com.xpzheng.blog.dto.CategoryDTO;
import com.xpzheng.blog.service.CategoryService;

/**
 * @author xpzheng
 *
 */
@RestController
@RequestMapping("category")
public class CategoryController extends BaseController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping()
    public AjaxResult listChildren(Long pid) {
        return success(categoryService.listChildren(pid, null));
    }

    @GetMapping("{id}")
    public AjaxResult getCategory(@PathVariable(name = "id") Long id) {
        CategoryDTO categoryDTO = categoryService.get(id);
        if (categoryDTO == null) {
            return failed("没有找到该分类");
        }
        return success(categoryDTO);
    }

    @PostMapping
    public AjaxResult add(@RequestBody(required = true) CategoryDTO categoryDTO) {
        boolean result = categoryService.add(categoryDTO);
        return result ? success() : failed();
    }

    @DeleteMapping("{id}")
    public AjaxResult remove(@PathVariable(name = "id") Long id) {
        boolean result = categoryService.remove(id);
        return result ? success() : failed();
    }

}
