package com.cheesun.service.impl;

import com.cheesun.mapper.CarouselMapper;
import com.cheesun.pojo.Carousel;
import com.cheesun.service.CarouselService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @auther cheesun
 * @date 2020/12/12
 * @time 15:06
 * @description
 */
@Service
public class CarouselServiceImpl implements CarouselService {
    @Autowired
    private CarouselMapper carouselMapper;
    @Override
    public List<Carousel> queryAll(Integer isShow) {
        Example example = new Example(Carousel.class);
        example.orderBy("sort").asc();
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("isShow", isShow);
        return carouselMapper.selectByExample(example);
    }
}
