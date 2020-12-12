package com.cheesun.pojo.vo;

import lombok.Data;

/**
 * @author: cheesun
 * @date: 2020/12/12
 * @description:
 */
@Data
public class SubCategoryVO {
    private Integer subId;

    private String subName;

    private String subType;

    private Integer subFatherId;
}
