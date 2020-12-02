package com.cheesun.enums;

/**
 * @Desc 性别枚举
 * @author cheesun
 * @date 2020/11/14
 */
public enum SexEnum {
    WOMAN(0, "女"), MAN(1, "男"), SECRET(2, "保密");

    public final Integer type;
    public final String sex;

    SexEnum(Integer type, String sex) {
        this.type = type;
        this.sex = sex;
    }
}
