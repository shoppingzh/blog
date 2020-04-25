package com.xpzheng.blog.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xpzheng.blog.dto.PageDTO;
import com.xpzheng.blog.dto.TagDTO;
import com.xpzheng.blog.mapper.TagMapper;
import com.xpzheng.blog.model.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class TagService {

    @Autowired
    private TagMapper tagMapper;

    public void add(Tag tag) {
        tagMapper.insert(tag);
    }

    public boolean remove(long id) {
        return tagMapper.deleteById(id) > 0;
    }

    public Tag get(long id) {
        return tagMapper.selectById(id);
    }

    /**
     * 获取标签列表
     * @param tag
     * @return
     */
    public PageDTO<TagDTO> list(int page, int pageSize) {
        IPage<Tag> p = new Page<Tag>(page, pageSize);
        tagMapper.selectPage(p, null);
        return PageDTO.valueOf(p, new DTOConverter());
    }

    class DTOConverter implements Function<Tag, TagDTO> {

        @Override
        public TagDTO apply(Tag t) {
            TagDTO dto = new TagDTO();
            dto.setId(String.valueOf(t.getId()));
            dto.setName(t.getName());
            dto.setTheme(t.getTheme());
            return dto;
        }

    }

}
