package com.cheesun.service;

import com.cheesun.pojo.Category;
import com.cheesun.pojo.vo.CategoryVO;
import com.cheesun.pojo.vo.NewItemsVO;

import java.util.List;

/**
 * @auther cheesun
 * @date 2020/12/12
 * @time 15:33
 * @description
 */
public interface CategoryService {
    /**
     * 查询所有一级分类
     * @return
     */
    List<Category> queryRoot();

    /**
     * 根据一级分类查询子分类列表
     * @param rootId
     * @return
     */
    List<CategoryVO> getSubList(Integer rootId);

    /**
     * 查询首页每个一级分类下的6条最新商品数据
     * @param rootCatId
     * @return
     */
    List<NewItemsVO> getSixNewItemsLazy(Integer rootCatId);
}
