package com.kang.controller;

import java.util.Arrays;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kang.pojo.ItemsEntity;
import com.kang.service.ItemsService;
import com.kang.common.utils.PageUtils;
import com.kang.common.utils.R;



/**
 * 商品表 商品信息相关表：分类表，商品图片表，商品规格表，商品参数表
 *
 * @author kang
 * @email 784706982@qq.com
 * @date 2021-04-24 10:11:01
 */
@RestController
@RequestMapping("kang/items")
public class ItemsController {
    @Autowired
    private ItemsService itemsService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = itemsService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") String id){
		ItemsEntity items = itemsService.getById(id);

        return R.ok().put("items", items);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody ItemsEntity items){
		itemsService.save(items);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody ItemsEntity items){
		itemsService.updateById(items);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody String[] ids){
		itemsService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
