package com.xpzheng.blog.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xpzheng.blog.dto.PageDTO;
import com.xpzheng.blog.dto.TagDTO;
import com.xpzheng.blog.mapper.TagMapper;
import com.xpzheng.blog.model.Tag;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.function.Function;

@Service
public class TagService {

    @Autowired
    private TagMapper tagMapper;

    /**
     * 新增标签
     * @param tagDTO
     */
    public boolean add(TagDTO tagDTO) {
        Tag tag = tag(tagDTO);
        tag.setGmtCreate(new Date());
        return tagMapper.insert(tag) > 0;
    }

    /**
     * 删除指定标签
     * @param id 标签ID
     * @return
     */
    public boolean remove(long id) {
        Tag tag = new Tag();
        tag.setId(id);
        tag.setDeleted(true);
        return tagMapper.updateById(tag) > 0;
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
        return tagMapper.updateById(tag) > 0;
    }

    /**
     * 获取指定标签
     * @param id 标签ID
     * @return
     */
    public TagDTO get(long id) {
        return tagDTO(tagMapper.selectOne(new QueryWrapper<Tag>().eq("deleted", false).eq("id", id)));
    }

    /**
     * 通过标签名获取标签
     * @param name
     * @return
     */
    public TagDTO getByName(String name) {
        QueryWrapper<Tag> wrapper = new QueryWrapper<Tag>().eq("deleted", false).eq("name", name);
        return tagDTO(tagMapper.selectOne(wrapper));
    }

    /**
     * 分页获取标签列表
     * @param page 当前页
     * @param pageSize 每页数量
     * @param tagDTO 查询条件
     * @return
     */
    public PageDTO<TagDTO> page(int page, int pageSize, TagDTO tagDTO) {
        QueryWrapper<Tag> wrapper = new QueryWrapper<Tag>().eq("deleted", false);
        if (StringUtils.isNotBlank(tagDTO.getName())) {
            wrapper.eq("name", tagDTO.getName());
        } else if (StringUtils.isNotBlank(tagDTO.getKeyword())) {
            wrapper.like("name", tagDTO.getKeyword());
        }
        IPage<Tag> p = new Page<Tag>(page, pageSize);
        tagMapper.selectPage(p, wrapper);
        return PageDTO.valueOf(p, new Function<Tag, TagDTO>() {
            @Override
            public TagDTO apply(Tag tag) {
                return tagDTO(tag);
            }
        });
    }

    /**
     * 获取标签列表
     * @param tagDTO
     * @return
     */
    public List<TagDTO> list(TagDTO tagDTO) {
        return page(1, -1, tagDTO).getData();
    }

    public TagDTO tagDTO(Tag tag) {
        if (tag == null) {
            return null;
        }
        TagDTO dto = new TagDTO();
        dto.setId(String.valueOf(tag.getId()));
        dto.setName(tag.getName());
        dto.setTheme(tag.getTheme());
        return dto;
    }

    public Tag tag(TagDTO dto) {
        if (dto == null) {
            return null;
        }
        Tag tag = new Tag();
        tag.setId(Long.valueOf(dto.getId()));
        tag.setTheme(dto.getTheme());
        tag.setName(dto.getName());
        return tag;
    }

}
