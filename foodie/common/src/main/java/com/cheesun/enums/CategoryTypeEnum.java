package com.cheesun.enums;

/**
 * @auther cheesun
 * @date 2020/12/12
 * @time 15:35
 * @description
 */
public enum CategoryTypeEnum {
    LEVEL_ONE(1, "一级分类"), LEVEL_TWO(2, "二级分类"), LEVEL_THREE(3, "三级分类");

    public final Integer type;
    public final String value;

    CategoryTypeEnum(Integer type, String value) {
        this.type = type;
        this.value = value;
    }
}
