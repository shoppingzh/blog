/**
 * 
 */
package com.xpzheng.blog.service;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.xpzheng.blog.dto.PageDTO;
import com.xpzheng.blog.dto.TagDTO;

/**
 * @author xpzheng
 *
 */
@SpringBootTest
public class TagServiceTest {

    @Autowired
    private TagService tagService;

    @Test
    public void add() {
        TagDTO tag = new TagDTO();
        tag.setName("React");
        tagService.add(tag);
    }

    @Test
    public void remove() {
        tagService.remove(1);
        TagDTO tagDTO = tagService.get(1);
        assert tagDTO == null;
    }

    @Test
    public void get() {
        TagDTO tagDTO = tagService.get(1);
        System.out.println(tagDTO);
    }

    @Test
    public void getByName() {
        TagDTO tag = tagService.getByName("Vue");
        System.out.println(tag);
    }

    @Test
    public void page() {
        PageDTO<TagDTO> list = tagService.page(1, 0, null);
        System.out.println(list);
    }

    @Test
    public void list() {
        TagDTO tag = new TagDTO();
        tag.setKeyword("Vu");
        List<TagDTO> list = tagService.list(tag);
        System.out.println(list);
    }
    
}
