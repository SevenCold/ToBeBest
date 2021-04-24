package com.kang.controller;

import java.util.Arrays;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kang.pojo.CarouselEntity;
import com.kang.service.CarouselService;
import com.kang.common.utils.PageUtils;
import com.kang.common.utils.R;



/**
 * 轮播图 
 *
 * @author kang
 * @email 784706982@qq.com
 * @date 2021-04-24 10:11:03
 */
@RestController
@RequestMapping("kang/carousel")
public class CarouselController {
    @Autowired
    private CarouselService carouselService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = carouselService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") String id){
		CarouselEntity carousel = carouselService.getById(id);

        return R.ok().put("carousel", carousel);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody CarouselEntity carousel){
		carouselService.save(carousel);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody CarouselEntity carousel){
		carouselService.updateById(carousel);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody String[] ids){
		carouselService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
