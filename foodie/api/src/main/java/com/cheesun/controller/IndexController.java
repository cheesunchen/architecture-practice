package com.cheesun.controller;

import com.cheesun.enums.YesOrNoEnum;
import com.cheesun.pojo.Carousel;
import com.cheesun.pojo.Category;
import com.cheesun.pojo.vo.CategoryVO;
import com.cheesun.pojo.vo.NewItemsVO;
import com.cheesun.service.CarouselService;
import com.cheesun.service.CategoryService;
import com.cheesun.utils.JSONResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @auther cheesun
 * @date 2020/12/12
 * @time 15:12
 * @description
 */
@RestController
@RequestMapping("/index/")
@Api(value = "首页", tags = {"用于首页展示的相关接口"})
public class IndexController {

    @Autowired
    private CarouselService carouselService;

    @Autowired
    private CategoryService categoryService;

    @ApiOperation(value = "获取首页轮播图列表", notes = "获取首页轮播图列表", httpMethod = "GET")
    @GetMapping("carousel")
    public JSONResult carousel() {
        List<Carousel> list = carouselService.queryAll(YesOrNoEnum.YES.type);
        return JSONResult.ok(list);
    }

    @ApiOperation(value = "获取一级分类列表", notes = "获取一级分类列表", httpMethod = "GET")
    @GetMapping("cats")
    public JSONResult categoryRoot() {
        List<Category> list = categoryService.queryRoot();
        return JSONResult.ok(list);
    }

    @ApiOperation(value = "获取商品子分类", notes = "获取商品子分类", httpMethod = "GET")
    @GetMapping("subCat/{rootCatId}")
    public JSONResult subCat(
            @ApiParam(name = "rootCatId", value = "一级分类id", required = true)
            @PathVariable Integer rootCatId) {
        if (rootCatId == null || rootCatId <= 0) {
            return JSONResult.errorMsg("分类不存在");
        }
        List<CategoryVO> list = categoryService.getSubList(rootCatId);
        return JSONResult.ok(list);
    }

    @ApiOperation(value = "查询每个一级分类下的最新6条商品数据", notes = "查询每个一级分类下的最新6条商品数据", httpMethod = "GET")
    @GetMapping("sixNewItems/{rootCatId}")
    public JSONResult sixNewItems(
            @ApiParam(name = "rootCatId", value = "一级分类id", required = true)
            @PathVariable Integer rootCatId) {

        if (rootCatId == null) {
            return JSONResult.errorMsg("分类不存在");
        }

        List<NewItemsVO> list = categoryService.getSixNewItemsLazy(rootCatId);
        return JSONResult.ok(list);
    }
}
