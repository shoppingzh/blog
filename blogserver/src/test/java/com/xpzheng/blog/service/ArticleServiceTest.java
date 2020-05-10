/**
 * 
 */
package com.xpzheng.blog.service;

import java.util.ArrayList;
import java.util.Date;
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
        articleDTO.setContent("<h3>本文将介绍如何在当前时间点，将服务器版本的 Ubuntu 18.04 LTS 升级为最新的 Ubuntu 20.04 LTS，以及升级过程中的一些细节，希望能帮到跃跃欲试的 Ubuntu 同好。将数据进行备份等操作需要自行处理，另外确保网络稳定，建议都在服务器跳板机上进行操作，更为稳妥。当前这篇内容已经运行在 Ubuntu 20.04 LTS 系统环境中，:)</h3>");
        articleDTO.setPlainContent("本文将介绍如何在当前时间点，将服务器版本的 Ubuntu 18.04 LTS 升级为最新的 Ubuntu 20.04 LTS，以及升级过程中的一些细节，希望能帮到跃跃欲试的 Ubuntu 同好。将数据进行备份等操作需要自行处理，另外确保网络稳定，建议都在服务器跳板机上进行操作，更为稳妥。当前这篇内容已经运行在 Ubuntu 20.04 LTS 系统环境中，:)");

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
    
    @Test
    public void statWithDay() {
        System.out.println(articleService.statWithDay(new Date()));
    }

}
