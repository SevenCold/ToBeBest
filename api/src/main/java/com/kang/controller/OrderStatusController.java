package com.kang.controller;

import java.util.Arrays;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kang.pojo.OrderStatusEntity;
import com.kang.service.OrderStatusService;
import com.kang.common.utils.PageUtils;
import com.kang.common.utils.R;



/**
 * 订单状态表 订单的每个状态更改都需要进行记录
10：待付款  20：已付款，待发货  30：已发货，待收货（7天自动确认）  40：交易成功（此时可以评价）50：交易关闭（待付款时，用户取消 或 长时间未付款，系统识别后自动关闭）
退货/退货，此分支流程不做，所以不加入
 *
 * @author kang
 * @email 784706982@qq.com
 * @date 2021-04-24 10:10:59
 */
@RestController
@RequestMapping("kang/orderstatus")
public class OrderStatusController {
    @Autowired
    private OrderStatusService orderStatusService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = orderStatusService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{orderId}")
    public R info(@PathVariable("orderId") String orderId){
		OrderStatusEntity orderStatus = orderStatusService.getById(orderId);

        return R.ok().put("orderStatus", orderStatus);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody OrderStatusEntity orderStatus){
		orderStatusService.save(orderStatus);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody OrderStatusEntity orderStatus){
		orderStatusService.updateById(orderStatus);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody String[] orderIds){
		orderStatusService.removeByIds(Arrays.asList(orderIds));

        return R.ok();
    }

}
