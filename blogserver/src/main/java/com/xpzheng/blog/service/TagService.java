package com.xpzheng.blog.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xpzheng.blog.dto.PageDTO;
import com.xpzheng.blog.dto.TagDTO;
import com.xpzheng.blog.mapper.TagMapper;
import com.xpzheng.blog.model.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.function.Function;

@Service
public class TagService {

    @Autowired
    private TagMapper tagMapper;

    public void add(Tag tag) {
        tagMapper.insert(tag);
    }

    /**
     * 删除指定标签
     * @param id 标签ID
     * @return
     */
    public boolean remove(long id) {
        return tagMapper.deleteById(id) > 0;
    }

    /**
     * 获取指定标签
     * @param id 标签ID
     * @return
     */
    public Tag get(long id) {
        return tagMapper.selectById(id);
    }

    /**
     * 获取标签列表
     * @param page 当前页
     * @param pageSize 每页数量
     * @return
     */
    public PageDTO<TagDTO> list(int page, int pageSize) {
        IPage<Tag> p = new Page<Tag>(page, pageSize);
        tagMapper.selectPage(p, null);
        return PageDTO.valueOf(p, new Function<Tag, TagDTO>() {

            @Override
            public TagDTO apply(Tag t) {
                return null;
            }
        });
    }

    /**
     * 更新指定的标签
     * @param tagDTO
     * @return
     */
    public boolean update(TagDTO tagDTO) {
        Tag tag = new Tag();
        tag.setId(Long.valueOf(tagDTO.getId()));
        tag.setName(tagDTO.getName());
        tag.setTheme(tagDTO.getTheme());
        tag.setGmtModify(new Date());
        tagMapper.updateById(tag);
        return true;
    }

    public TagDTO dto(Tag tag) {
        if (tag == null) {
            return null;
        }
        TagDTO dto = new TagDTO();
        dto.setId(String.valueOf(tag.getId()));
        dto.setName(tag.getName());
        dto.setTheme(tag.getTheme());
        return dto;
    }

}
