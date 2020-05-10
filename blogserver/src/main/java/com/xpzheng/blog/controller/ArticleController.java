/**
 * 
 */
package com.xpzheng.blog.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.xpzheng.blog.service.ArticleService;
import com.xpzheng.blog.controller.data.AjaxResult;
import com.xpzheng.blog.dto.ArticleDTO;

/**
 * @author xpzheng
 *
 */
@RestController
@RequestMapping("article")
public class ArticleController extends BaseController {

    @Autowired
    private ArticleService articleService;

    @PostMapping
    public AjaxResult add(@RequestBody ArticleDTO articleDTO) {
        String result = articleService.add(articleDTO);
        return result != null ? success(result) : failed();
    }

    @PutMapping
    public AjaxResult update(@RequestBody ArticleDTO articleDTO) {
        System.out.println(articleDTO);
        boolean result = articleService.update(articleDTO);
        return result ? success() : failed();
    }

    @DeleteMapping("{id}")
    public AjaxResult remove(@PathVariable(name = "id") String id) {
        boolean result = articleService.remove(id);
        return result ? success() : failed();
    }

    @PostMapping("/publish/{id}")
    public AjaxResult publish(@PathVariable(name = "id") String id) {
        return articleService.updatePublish(id, true) ? success() : failed();
    }

    @GetMapping("{id}")
    public AjaxResult get(@PathVariable(name = "id") String id) {
        ArticleDTO articleDTO = articleService.get(id);
        return articleDTO != null ? success(articleDTO) : failed("没有找到该文章");
    }

    @GetMapping
    public AjaxResult page(@RequestParam(defaultValue = DEFAULT_PAGE) Integer page,
            @RequestParam(defaultValue = DEFAULT_PAGESIZE) Integer pageSize) {
        return success(articleService.page(page, pageSize, null));
    }

    @GetMapping("stat/day")
    public AjaxResult statWithDay() {
        return success(articleService.statWithDay(new Date()));
    }

}
