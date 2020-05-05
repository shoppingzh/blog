/**
 * 
 */
package com.xpzheng.blog.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xpzheng.blog.dto.ArticleDTO;
import com.xpzheng.blog.dto.CategoryDTO;
import com.xpzheng.blog.dto.FileDTO;
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
import com.xpzheng.blog.util.ArticleUtils;

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
    @Autowired
    private FileService fileService;

    /**
     * 添加文章
     * @param articleDTO
     * @return
     */
    public String add(ArticleDTO articleDTO) {
        if (articleDTO == null) {
            throw new IllegalArgumentException("articleDTO can not be null!");
        }
        // 保存文章
        Article article = new Article();
        article.setTitle(articleDTO.getTitle());
        article.setDraft(articleDTO.isDraft());
        article.setHot(articleDTO.getHot());
        CategoryDTO category = articleDTO.getCategory();
        if (category != null && StringUtils.isNotBlank(category.getId())) {
            article.setCid(Long.valueOf(category.getId()));
        }
        article.setGmtCreate(new Date());
        article.setSummary(ArticleUtils.createSummary(articleDTO.getPlainContent()));
        FileDTO thumbnail = articleDTO.getThumbnail();
        if (thumbnail != null && StringUtils.isNoneBlank(thumbnail.getId())) {
            article.setThumbnail(thumbnail.getId());
        }
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
        if (articleDTO == null) {
            throw new IllegalArgumentException("articleDTO can not be null!");
        }
        Article article = new Article();
        article.setId(articleDTO.getId());
        article.setTitle(articleDTO.getTitle());
        article.setDraft(articleDTO.isDraft());
        article.setHot(articleDTO.getHot());
        article.setSummary(ArticleUtils.createSummary(articleDTO.getPlainContent()));
        article.setGmtModify(new Date());
        if (articleDTO.getCategory() != null && StringUtils.isNotBlank(articleDTO.getCategory().getId())) {
            article.setCid(Long.valueOf(articleDTO.getCategory().getId()));
        }
        FileDTO thumbnail = articleDTO.getThumbnail();
        if (thumbnail != null && StringUtils.isNoneBlank(thumbnail.getId())) {
            article.setThumbnail(thumbnail.getId());
        }
        if (articleMapper.updateById(article) <= 0) {
            return false;
        }
        List<TagDTO> tags = articleDTO.getTags();
        if (CollectionUtils.isEmpty(tags)) {
            articleTagMapper.delete(new QueryWrapper<ArticleTag>().eq("aid", articleDTO.getId()));
        } else {
            List<ArticleTag> articleTags = articleTagMapper
                    .selectList(new QueryWrapper<ArticleTag>().eq("aid", articleDTO.getId()));
            List<TagDTO> tmpTags = new ArrayList<>(tags);
            // 留下需要添加的
            tags.removeIf(new Predicate<TagDTO>() {
                @Override
                public boolean test(TagDTO t) {
                    for (ArticleTag at : articleTags) {
                        if (at.getTid().equals(Long.valueOf(t.getId()))) {
                            return true;
                        }
                    }
                    return false;
                }
            });
            for (TagDTO tag : tags) {
                ArticleTag at = new ArticleTag();
                at.setAid(article.getId());
                at.setTid(Long.valueOf(tag.getId()));
                at.setGmtCreate(new Date());
                articleTagMapper.insert(at);
            }
            // 留下需要去除的
            articleTags.removeIf(new Predicate<ArticleTag>() {
                @Override
                public boolean test(ArticleTag at) {
                    for (TagDTO tag : tmpTags) {
                        if (at.getTid().equals(Long.valueOf(tag.getId()))) {
                            return true;
                        }
                    }
                    return false;
                }
            });
            for (ArticleTag at : articleTags) {
                articleTagMapper
                        .delete(new QueryWrapper<ArticleTag>().eq("aid", articleDTO.getId()).eq("tid", at.getTid()));
            }
        }
        return true;
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
        return articleDTO(articleMapper.selectById(id), true, true, true, true);
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
                return articleDTO(t, true, true, true, true);
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
        dto.setHot(article.getHot());
        dto.setCreateTime(article.getGmtCreate());
        dto.setModifyTime(article.getGmtModify());
        return dto;
    }

    private ArticleDTO articleDTO(Article article, boolean withContent, boolean withCategory, boolean withTags, boolean withThumbnail) {
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
        String thumbnail = article.getThumbnail();
        if (withThumbnail && StringUtils.isNotBlank(thumbnail)) {
            FileDTO fileDTO = fileService.get(thumbnail);
            dto.setThumbnail(fileDTO);
        }
        return dto;
    }

}
