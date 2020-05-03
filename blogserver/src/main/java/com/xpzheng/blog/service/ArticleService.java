/**
 * 
 */
package com.xpzheng.blog.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.function.Function;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xpzheng.blog.dto.ArticleDTO;
import com.xpzheng.blog.dto.CategoryDTO;
import com.xpzheng.blog.dto.PageDTO;
import com.xpzheng.blog.dto.TagDTO;
import com.xpzheng.blog.mapper.ArticleContentMapper;
import com.xpzheng.blog.mapper.ArticleMapper;
import com.xpzheng.blog.mapper.ArticleTagMapper;
import com.xpzheng.blog.mapper.CategoryMapper;
import com.xpzheng.blog.mapper.TagMapper;
import com.xpzheng.blog.model.Article;
import com.xpzheng.blog.model.ArticleContent;
import com.xpzheng.blog.model.ArticleTag;
import com.xpzheng.blog.model.Category;
import com.xpzheng.blog.model.Tag;

/**
 * @author xpzheng
 *
 */
@Service
public class ArticleService {

    @Autowired
    private ArticleMapper articleMapper;
    @Autowired
    private ArticleContentMapper articleContentMapper;
    @Autowired
    private ArticleTagMapper articleTagMapper;
    @Autowired
    private CategoryMapper categoryMapper;
    @Autowired
    private TagMapper tagMapper;

    /**
     * 添加文章
     * @param articleDTO
     * @return
     */
    public String add(ArticleDTO articleDTO) {
        // 保存文章
        Article article = new Article();
        article.setTitle(articleDTO.getTitle());
        article.setDraft(articleDTO.isDraft());
        CategoryDTO category = articleDTO.getCategory();
        if (category != null && StringUtils.isNotBlank(category.getId())) {
            article.setCid(Long.valueOf(category.getId()));
        }
        article.setGmtCreate(new Date());
        articleMapper.insert(article);

        String id = article.getId();
        if (id == null) {
            return null;
        }
        // 保存关联标签
        List<TagDTO> tags = articleDTO.getTags();
        if (CollectionUtils.isNotEmpty(tags)) {
            for (TagDTO tag : tags) {
                if (StringUtils.isBlank(tag.getId())) {
                    continue;
                }
                ArticleTag at = new ArticleTag();
                at.setAid(id);
                at.setTid(Long.valueOf(tag.getId()));
                at.setGmtCreate(new Date());
                articleTagMapper.insert(at);
            }
        }
        // 保持关联文章内容
        ArticleContent articleContent = new ArticleContent();
        articleContent.setAid(id);
        articleContent.setContent(articleDTO.getContent());
        articleContent.setPlainContent(articleDTO.getPlainContent());
        articleContentMapper.insert(articleContent);
        return id;
    }

    /**
     * 删除文章
     * @param id
     * @return
     */
    public boolean remove(String id) {
        Article article = new Article();
        article.setId(id);
        article.setDeleted(true);
        return articleMapper.updateById(article) > 0;
    }

    /**
     * 更新文章
     * @param articleDTO
     * @return
     */
    public boolean update(ArticleDTO articleDTO) {
        return false;
    }

    /**
     * 更新发布状态
     * @param id               文章id
     * @param published        是否发布
     * @return
     */
    public boolean updatePublish(String id, boolean published) {
        Article article = new Article();
        article.setId(id);
        article.setDraft(!published);
        return articleMapper.updateById(article) > 0;
    }

    /**
     * 获取指定文章
     * @param id            文章id
     * @return
     */
    public ArticleDTO get(String id) {
        return articleDTO(articleMapper.selectById(id), true, true, true);
    }

    /**
     * 分页查询文章
     * @param page              当前页
     * @param pageSize          每页数量
     * @param articleDTO        查询条件
     * @return
     */
    public PageDTO<ArticleDTO> page(int page, int pageSize, ArticleDTO articleDTO) {
        QueryWrapper<Article> wrapper = new QueryWrapper<Article>().eq("deleted", false);
        IPage<Article> p = articleMapper.selectPage(new Page<Article>(page, pageSize), wrapper);
        return PageDTO.valueOf(p, new Function<Article, ArticleDTO>() {
            @Override
            public ArticleDTO apply(Article t) {
                return articleDTO(t, true, true, true);
            }
        });
    }

    private ArticleDTO articleDTO(Article article) {
        if (article == null) {
            return null;
        }
        ArticleDTO dto = new ArticleDTO();
        dto.setId(article.getId());
        dto.setTitle(article.getTitle());
        dto.setSummary(article.getSummary());
        dto.setDraft(article.getDraft());
        dto.setCreateTime(article.getGmtCreate());
        dto.setModifyTime(article.getGmtModify());
        return dto;
    }

    private ArticleDTO articleDTO(Article article, boolean withContent, boolean withCategory, boolean withTags) {
        ArticleDTO dto = articleDTO(article);
        if (withContent) {
            ArticleContent articleContent = articleContentMapper.selectById(article.getId());
            if (articleContent != null) {
                dto.setContent(articleContent.getContent());
                dto.setPlainContent(articleContent.getPlainContent());
            }
        }
        if (withCategory && article.getCid() != null) {
            Category category = categoryMapper.selectById(article.getCid());
            if (category != null) {
                CategoryDTO categoryDTO = new CategoryDTO();
                categoryDTO.setId(String.valueOf(category.getId()));
                categoryDTO.setName(category.getName());
                dto.setCategory(categoryDTO);
            }
        }
        if (withTags) {
            List<ArticleTag> articleTags = articleTagMapper
                    .selectList(new QueryWrapper<ArticleTag>().eq("aid", article.getId()));
            List<TagDTO> tags = new ArrayList<TagDTO>();
            if (CollectionUtils.isNotEmpty(articleTags)) {
                for (ArticleTag articleTag : articleTags) {
                    Tag tag = tagMapper.selectById(articleTag.getTid());
                    if (tag != null) {
                        TagDTO tagDTO = new TagDTO();
                        tagDTO.setId(String.valueOf(tag.getId()));
                        tagDTO.setName(tag.getName());
                        tagDTO.setTheme(tag.getTheme());
                        tags.add(tagDTO);
                    }
                }
            }
            dto.setTags(tags);
        }
        return dto;
    }

}
