package com.cheesun.service;

import com.cheesun.pojo.Carousel;

import java.util.List;

/**
 * @auther cheesun
 * @date 2020/12/12
 * @time 15:05
 * @description
 */
public interface CarouselService {

    List<Carousel> queryAll(Integer isShow);
}
