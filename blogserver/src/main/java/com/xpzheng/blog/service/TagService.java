package com.xpzheng.blog.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.xpzheng.blog.mapper.TagMapper;
import com.xpzheng.blog.model.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagService {

    @Autowired
    private TagMapper tagMapper;

    /**
     * 获取指定标签
     * @param id
     * @return
     */
    public Tag getById(Long id) {
        return tagMapper.selectById(id);
    }

    /**
     * 分页查询
     * @return
     */
    public Page<Tag> page(Tag tag, int pageNum, int pageSize){
//        PageInfo<Tag> page = PageHelper.startPage(pageNum, pageSize).doSelectPageInfo(() -> {
//           tagMapper.select(null);
//        });
        return PageHelper.startPage(pageNum, pageSize).doSelectPage(() -> {
            tagMapper.select(null);
        });
    }

    /**
     * 获取标签列表
     * @param tag
     * @return
     */
    public List<Tag> list(Tag tag) {
        return tagMapper.select(tag);
    }

}
