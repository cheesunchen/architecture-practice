package com.cheesun.pojo.vo;

import lombok.Data;

import java.util.List;

/**
 * @author: cheesun
 * @date: 2020/12/12
 * @description: 二级分类vo
 */
@Data
public class CategoryVO {
    private Integer id;

    private String name;

    private String type;

    private Integer fatherId;

    private List<SubCategoryVO> subCatList;
}
