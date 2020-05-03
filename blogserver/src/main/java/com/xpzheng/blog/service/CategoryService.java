/**
 * 
 */
package com.xpzheng.blog.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.function.Function;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.xpzheng.blog.dto.CategoryDTO;
import com.xpzheng.blog.mapper.CategoryMapper;
import com.xpzheng.blog.model.Category;
import com.xpzheng.blog.service.util.TreePathUtils;

/**
 * @author xpzheng
 *
 */
@Service
public class CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    /**
     * 添加分类
     * @param categoryDTO
     * @return
     */
    public boolean add(CategoryDTO categoryDTO) {
        Category cat = new Category();
        cat.setName(categoryDTO.getName());
        cat.setGmtCreate(new Date());
        if (categoryDTO.getParent() != null) {
            Category parent = categoryMapper.selectById(Long.valueOf(categoryDTO.getParent().getId()));
            if (parent != null) {
                cat.setPid(parent.getId());
                cat.setPath(TreePathUtils.joinPath(parent.getPath(), String.valueOf(parent.getId())));
            }
        }
        return categoryMapper.insert(cat) > 0;
    }

    /**
     * 删除某个分类(会同步删除所有子分类)
     * @param id
     * @return
     */
    public boolean remove(long id) {
        Function<Category, Boolean> func = new Function<Category, Boolean>() {
            @Override
            public Boolean apply(Category cat) {
                cat.setDeleted(true);
                return categoryMapper.updateById(cat) > 0;
            }
        };
        boolean result = func.apply(new Category(id));
        if (!result) {
            return false;
        }
        iterateChildren(id, func);
        return true;
    }

    /**
     * 迭代子分类
     * @param id             父分类id
     * @param callback       回调
     */
    private void iterateChildren(long id, Function<Category, Boolean> callback) {
        List<Category> children = categoryMapper.selectList(new QueryWrapper<Category>().eq("pid", id));
        if (CollectionUtils.isNotEmpty(children)) {
            for (Category child : children) {
                Boolean result = callback.apply(child);
                if (result != null && result) {
                    iterateChildren(child.getId(), callback);
                }
            }
        }
    }

    /**
     * 更新分类
     * @param categoryDTO
     * @return
     */
    public boolean update(CategoryDTO categoryDTO) {
        Category cat = new Category();
        cat.setId(Long.valueOf(categoryDTO.getId()));
        cat.setName(categoryDTO.getName());
        cat.setGmtModify(new Date());
        return categoryMapper.updateById(cat) > 0;
    }

    /**
     * 移动分类
     * @param id           需要移动的分类id
     * @param parentId     移动到的父分类id(可以为null)
     * @return
     */
    public boolean move(long id, Long parentId) {
        UpdateWrapper<Category> wrapper = new UpdateWrapper<Category>(new Category(id));
        if (parentId == null) {
            wrapper.set("pid", null);
            wrapper.set("path", null);
        } else {
            Category parent = categoryMapper.selectById(parentId); // FIXME 暂时不管软删除的情况
            if (parent == null) {
                return false;
            }
            wrapper.set("pid", parentId);
            wrapper.set("path", TreePathUtils.joinPath(parent.getPath(), String.valueOf(parentId)));
        }
        return categoryMapper.update(null, wrapper) > 0;
    }

    /**
     * 获取指定分类
     * @param id
     * @return
     */
    public CategoryDTO get(long id) {
        return categoryDTO(categoryMapper.selectById(id));
    }

    /**
     * 获取子分类列表
     * @param pid              父分类id
     * @param categoryDTO      查询条件
     * @return
     */
    @SuppressWarnings("unchecked")
    public List<CategoryDTO> listChildren(Long pid, CategoryDTO categoryDTO) {
        QueryWrapper<Category> wrapper = new QueryWrapper<Category>().eq("deleted", false);
        if (pid == null) {
            wrapper.isNull("pid");
        } else {
            wrapper.eq("pid", pid);
        }
        List<Category> list = categoryMapper.selectList(wrapper);
        if (CollectionUtils.isEmpty(list)) {
            return Collections.EMPTY_LIST;
        }
        List<CategoryDTO> dtos = new ArrayList<CategoryDTO>();
        for (Category cat : list) {
            dtos.add(categoryDTO(cat));
        }
        return dtos;
    }

    private CategoryDTO categoryDTO(Category cat) {
        if (cat == null) {
            return null;
        }
        CategoryDTO dto = new CategoryDTO();
        dto.setId(String.valueOf(cat.getId()));
        dto.setName(cat.getName());
        return dto;
    }
}
