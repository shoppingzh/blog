/**
 * 
 */
package com.xpzheng.blog.service;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.xpzheng.blog.dto.CategoryDTO;

/**
 * @author xpzheng
 *
 */
@SpringBootTest
public class CategoryServiceTest {

    @Autowired
    private CategoryService categoryService;
    
    @Test
    public void add() {
        CategoryDTO dto = new CategoryDTO();
        dto.setName("随笔4444");
        CategoryDTO parent = new CategoryDTO();
        parent.setId("3");
        dto.setParent(parent);
        categoryService.add(dto);
    }
    
    @Test
    public void remove() {
        boolean remove = categoryService.remove(2);
        System.out.println(remove);
    }
    
    @Test
    public void move() {
        boolean move = categoryService.move(4, 3L);
        System.out.println(move);
    }
    
    @Test
    public void listChildren() {
        List<CategoryDTO> parents = categoryService.listChildren(null, null);
        System.out.println(parents);
        List<CategoryDTO> children = categoryService.listChildren(2L, null);
        System.out.println(children);
    }
}
