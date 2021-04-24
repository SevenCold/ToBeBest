package com.kang.controller;

import java.util.Arrays;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kang.pojo.OrdersEntity;
import com.kang.service.OrdersService;
import com.kang.common.utils.PageUtils;
import com.kang.common.utils.R;



/**
 * 订单表 
 *
 * @author kang
 * @email 784706982@qq.com
 * @date 2021-04-24 10:11:00
 */
@RestController
@RequestMapping("kang/orders")
public class OrdersController {
    @Autowired
    private OrdersService ordersService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = ordersService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") String id){
		OrdersEntity orders = ordersService.getById(id);

        return R.ok().put("orders", orders);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody OrdersEntity orders){
		ordersService.save(orders);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody OrdersEntity orders){
		ordersService.updateById(orders);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody String[] ids){
		ordersService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
