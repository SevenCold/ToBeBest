package com.kang.controller;

import com.kang.BO.SubmitOrderBO;
import com.kang.common.utils.CookieUtils;
import com.kang.common.utils.R;
import com.kang.common.validator.ValidatorUtils;
import com.kang.common.validator.group.SubmitOrderGroup;
import com.kang.service.OrdersService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * 订单相关接口
 * @author kang
 * @email 784706982@qq.com
 * @date 2021-04-24 10:11:00
 */
@RestController
@RequestMapping("orders")
@Api(value = "订单相关", tags = {"SHOP-订单相关接口"})
public class OrdersController {
    @Autowired
    private OrdersService ordersService;

    /**
     * 用户创建订单
     * @param orderInfo 订单信息
     * @return 成功信息
     */
    @ApiOperation(value = "创建订单", notes = "用户创建订单", httpMethod = "POST")
    @PostMapping("/create")
    public R create(
            @ApiParam(name = "orderInfo", value = "订单信息", required = true)
            @RequestBody SubmitOrderBO orderInfo,
            HttpServletRequest request, HttpServletResponse response) {
        ValidatorUtils.validateEntity(orderInfo, SubmitOrderGroup.class);
        // 1.创建订单
        String orderId = ordersService.createOrder(orderInfo);
        // 2.清空删除购物车中已经购买商品
        CookieUtils.setCookie(request, response, "shopcart", "");
        // 3.发起支付
        // TODO: 2021/5/3 建立支付中心处理微信支付和支付宝支付
        return R.ok(orderId);
    }

}
