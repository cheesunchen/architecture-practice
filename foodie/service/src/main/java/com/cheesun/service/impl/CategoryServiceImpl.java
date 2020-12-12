package com.cheesun.service.impl;

import com.cheesun.enums.CategoryTypeEnum;
import com.cheesun.mapper.CategoryMapper;
import com.cheesun.mapper.CategoryMapperCustom;
import com.cheesun.pojo.Carousel;
import com.cheesun.pojo.Category;
import com.cheesun.pojo.vo.CategoryVO;
import com.cheesun.pojo.vo.NewItemsVO;
import com.cheesun.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @Autowired
    private CategoryMapperCustom categoryMapperCustom;

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public List<Category> queryRoot() {

        Example example = new Example(Category.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("type", CategoryTypeEnum.LEVEL_ONE.type);

        return categoryMapper.selectByExample(example);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public List<CategoryVO> getSubList(Integer rootId) {
        return categoryMapperCustom.getSubList(rootId);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public List<NewItemsVO> getSixNewItemsLazy(Integer rootCatId) {

        Map<String, Object> map = new HashMap<>();
        map.put("rootCatId", rootCatId);

        return categoryMapperCustom.getSixNewItemsLazy(map);
    }
}
