/**
 * 
 */
package com.xpzheng.blog.service;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.xpzheng.blog.dto.ArticleDTO;
import com.xpzheng.blog.dto.CategoryDTO;
import com.xpzheng.blog.dto.PageDTO;
import com.xpzheng.blog.dto.TagDTO;

/**
 * @author xpzheng
 *
 */
@SpringBootTest
public class ArticleServiceTest {

    @Autowired
    private ArticleService articleService;

    @Test
    public void add() {
        ArticleDTO articleDTO = new ArticleDTO();
        articleDTO.setTitle("我的第一篇文章");
        articleDTO.setDraft(true);
        articleDTO.setContent("<h3>Hello, world!</h3>");
        articleDTO.setPlainContent("Hello, world!");

        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setId("12");
        articleDTO.setCategory(categoryDTO);

        List<TagDTO> tags = new ArrayList<TagDTO>();
        TagDTO tag1 = new TagDTO();
        tag1.setId("28");
        TagDTO tag2 = new TagDTO();
        tag2.setId("29");
        TagDTO tag3 = new TagDTO();
        tag3.setId("30");
        tags.add(tag1);
        tags.add(tag2);
        tags.add(tag3);
        articleDTO.setTags(tags);

        articleService.add(articleDTO);
    }
    
    @Test
    public void page() {
        PageDTO<ArticleDTO> page = articleService.page(1, -1, null);
        System.out.println(page);
    }

}
