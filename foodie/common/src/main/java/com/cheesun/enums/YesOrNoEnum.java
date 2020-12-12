package com.cheesun.enums;

/**
 * @auther cheesun
 * @date 2020/12/12
 * @time 15:17
 * @description
 */
public enum YesOrNoEnum {
    NO(0, "否"), YES(1, "是");

    public final Integer type;
    public final String value;

    YesOrNoEnum(Integer type, String value) {
        this.type = type;
        this.value = value;
    }
}
