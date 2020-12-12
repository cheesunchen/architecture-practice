package com.cheesun.service.impl;

import com.cheesun.enums.CategoryTypeEnum;
import com.cheesun.mapper.CategoryMapper;
import com.cheesun.pojo.Carousel;
import com.cheesun.pojo.Category;
import com.cheesun.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @auther cheesun
 * @date 2020/12/12
 * @time 15:34
 * @description
 */
@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public List<Category> queryRoot() {

        Example example = new Example(Category.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("type", CategoryTypeEnum.LEVEL_ONE.type);

        return categoryMapper.selectByExample(example);
    }
}
