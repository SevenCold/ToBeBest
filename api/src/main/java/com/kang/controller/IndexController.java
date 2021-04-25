package com.kang.controller;

import com.kang.common.utils.R;
import com.kang.pojo.CarouselEntity;
import com.kang.pojo.CategoryEntity;
import com.kang.service.CarouselService;
import com.kang.service.CategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * 首页相关controller
 * @author kang
 * @email 784706982@qq.com
 * @date 2021-04-24 10:11:03
 */
@Api(value = "首页相关", tags = "首页相关接口")
@RestController
@RequestMapping("index")
public class IndexController {
    @Autowired
    private CarouselService carouselService;

    @Autowired
    private CategoryService categoryService;

    /**
     * 查询所有的轮播图信息
     * @return 轮播图信息list
     */
    @ApiOperation(value = "轮播图信息", httpMethod = "GET", notes = "轮播图信息")
    @RequestMapping("/carousel")
    public R list(){
        return R.ok(carouselService.lambdaQuery()
                .eq(CarouselEntity::getIsShow, true)
                .orderByAsc(CarouselEntity::getSort)
                .list());
    }

    /**
     * 查询所有的主分类
     * @return type=1的分类信息
     */
    @ApiOperation(value = "主分类信息", httpMethod = "GET", notes = "type=1的分类信息")
    @RequestMapping("/cats")
    public R cats(){
        return R.ok(categoryService.lambdaQuery()
                .eq(CategoryEntity::getType, 1)
                .orderByAsc(CategoryEntity::getId)
                .list());
    }

    /**
     * 根据父id查询所有的子分类
     * @return 子分类信息
     */
    @ApiOperation(value = "子分类信息", httpMethod = "GET", notes = "根据父id查询所有的子分类信息")
    @RequestMapping("/subCat/{fatherId}")
    public R subCat(
            @ApiParam(value = "父分类id", name = "fatherId", required = true, example = "1")
            @PathVariable Integer fatherId) {
        return R.ok(categoryService.getSubCatByFatherId(fatherId));
    }

    @ApiOperation(value = "查询每个一级分类下的最新6条商品数据", notes = "查询每个一级分类下的最新6条商品数据", httpMethod = "GET")
    @GetMapping("/sixNewItems/{rootCatId}")
    public R sixNewItems(
            @ApiParam(name = "rootCatId", value = "一级分类id", required = true)
            @PathVariable Integer rootCatId) {
        return R.ok(categoryService.getSixNewItemsLazy(rootCatId));
    }

}
