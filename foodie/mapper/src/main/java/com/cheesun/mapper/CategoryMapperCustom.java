package com.cheesun.mapper;

import com.cheesun.pojo.vo.CategoryVO;
import com.cheesun.pojo.vo.NewItemsVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface CategoryMapperCustom {
    List<CategoryVO> getSubList(Integer rootId);

    List<NewItemsVO> getSixNewItemsLazy(@Param("paramsMap") Map<String, Object> map);
}