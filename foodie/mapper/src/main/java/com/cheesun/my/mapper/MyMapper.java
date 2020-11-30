package com.cheesun.my.mapper;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * @auther 太一
 * @date 2020/11/30
 * @time 15:48
 * @description
 */
public interface MyMapper<T> extends Mapper<T>, MySqlMapper<T> {
}
