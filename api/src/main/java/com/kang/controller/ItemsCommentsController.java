package com.kang.controller;

import java.util.Arrays;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kang.pojo.ItemsCommentsEntity;
import com.kang.service.ItemsCommentsService;
import com.kang.common.utils.PageUtils;
import com.kang.common.utils.R;



/**
 * 商品评价表 
 *
 * @author kang
 * @email 784706982@qq.com
 * @date 2021-04-24 10:11:00
 */
@RestController
@RequestMapping("kang/itemscomments")
public class ItemsCommentsController {
    @Autowired
    private ItemsCommentsService itemsCommentsService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = itemsCommentsService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") String id){
		ItemsCommentsEntity itemsComments = itemsCommentsService.getById(id);

        return R.ok().put("itemsComments", itemsComments);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody ItemsCommentsEntity itemsComments){
		itemsCommentsService.save(itemsComments);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody ItemsCommentsEntity itemsComments){
		itemsCommentsService.updateById(itemsComments);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody String[] ids){
		itemsCommentsService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
