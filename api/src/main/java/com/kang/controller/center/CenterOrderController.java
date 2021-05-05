package com.kang.controller.center;

import com.kang.common.annotation.RequestParamRequired;
import com.kang.common.enums.ErrorMsgEnum;
import com.kang.common.enums.OrderStatusEnum;
import com.kang.common.enums.YesNoEnum;
import com.kang.common.utils.PageMap;
import com.kang.common.utils.R;
import com.kang.pojo.OrderStatusEntity;
import com.kang.pojo.OrdersEntity;
import com.kang.service.OrderStatusService;
import com.kang.service.OrdersService;
import com.kang.service.center.CenterOrdersService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Map;

/**
 * 订单管理接口
 * @author kang
 * @email 784706982@qq.com
 * @date 2021-05-04 16:31:02
 */
@Api(value = "订单管理", tags = "CENTER-订单管理接口")
@RestController
@RequestMapping("myorders")
public class CenterOrderController {

    @Autowired
    private CenterOrdersService centerOrdersService;

    @Autowired
    private OrderStatusService statusService;

    @Autowired
    private OrdersService ordersService;

    /**
     * 根据用户id查询订单信息
     * @param userId 用户id
     * @param orderStatus 订单状态
     * @param page 页码
     * @param pageSize 每页数量
     * @return 用户信息
     */
    @PostMapping("/query")
    @ApiOperation(value = "查询订单信息", httpMethod = "POST", notes = "根据用户id查询订单信息")
    public R query(
            @ApiParam(name = "userId", value = "用户ID", required = true)
            @RequestParamRequired @RequestParam String userId,
            @ApiParam(name = "orderStatus", value = "订单状态", required = true)
            @RequestParam String orderStatus,
            @ApiParam(name = "page", value = "页码")
            @RequestParam(required = false) String page,
            @ApiParam(name = "pageSize", value = "每页数量")
            @RequestParam(required = false) String pageSize) {
        Map<String, Object> paramsMap = PageMap.getInstance(page, pageSize)
                .put("userId", userId)
                .put("orderStatus", orderStatus)
                .getParamsMap();
        return R.ok(centerOrdersService.queryMyOrders(paramsMap));
    }


    /**
     * 模拟商家发货 将订单状态改为 WAIT_RECEIVE 已发货，待收货
     * @param orderId 订单id
     * @return 用户信息
     */
    @GetMapping("/deliver")
    @ApiOperation(value="商家发货", notes="商家发货", httpMethod = "GET")
    public R deliver(
            @ApiParam(name = "orderId", value = "订单ID", required = true)
            @RequestParamRequired @RequestParam String orderId) {
        statusService.lambdaUpdate()
                .set(OrderStatusEntity::getOrderStatus, OrderStatusEnum.WAIT_RECEIVE.getValue())
                .eq(OrderStatusEntity::getOrderStatus, OrderStatusEnum.WAIT_DELIVER.getValue())
                .eq(OrderStatusEntity::getOrderId, orderId)
                .update();
        return R.ok();
    }

    /**
     * 用户确认收货
     * @param orderId 订单id
     * @param userId 用户id
     * @return 确认收货成功
     */
    @ApiOperation(value="用户确认收货", notes="用户确认收货", httpMethod = "POST")
    @PostMapping("/confirmReceive")
    public R confirmReceive(
            @ApiParam(name = "orderId", value = "订单ID", required = true)
            @RequestParamRequired @RequestParam String orderId,
            @ApiParam(name = "userId", value = "用户id", required = true)
            @RequestParamRequired @RequestParam String userId) {
        // 判断用户id和订单id是否匹配
        OrdersEntity existedOrder = ordersService.getOrderByUserId(orderId, userId);
        if (existedOrder == null) {
            return ErrorMsgEnum.ORDER_NOT_EXIST.getR();
        }
        statusService.lambdaUpdate()
                .set(OrderStatusEntity::getOrderStatus, OrderStatusEnum.SUCCESS.getValue())
                .eq(OrderStatusEntity::getOrderStatus, OrderStatusEnum.WAIT_RECEIVE.getValue())
                .eq(OrderStatusEntity::getOrderId, orderId)
                .update();
        return R.ok();
    }

    /**
     * 用户删除订单
     * @param orderId 订单id
     * @param userId 用户id
     * @return 用户删除订单成功
     */
    @ApiOperation(value="用户删除订单", notes="用户删除订单", httpMethod = "POST")
    @PostMapping("/delete")
    public R delete(
            @ApiParam(name = "orderId", value = "订单ID", required = true)
            @RequestParamRequired @RequestParam String orderId,
            @ApiParam(name = "userId", value = "用户id", required = true)
            @RequestParamRequired @RequestParam String userId) {
        // 判断用户id和订单id是否匹配
        OrdersEntity existedOrder = ordersService.getOrderByUserId(orderId, userId);
        if (existedOrder == null) {
            return ErrorMsgEnum.ORDER_NOT_EXIST.getR();
        }
        ordersService.lambdaUpdate()
                .set(OrdersEntity::getIsDelete, YesNoEnum.YES.getCode())
                .set(OrdersEntity::getUpdatedTime, new Date())
                .eq(OrdersEntity::getId, orderId)
                .eq(OrdersEntity::getUserId, userId)
                .update();
        return R.ok();
    }

    /**
     * 获得不同状态订单数量
     * @param userId 用户id
     * @return 订单数量
     */
    @ApiOperation(value = "获得订单数量", notes = "获得不同状态订单数量", httpMethod = "POST")
    @PostMapping("/statusCounts")
    public R statusCounts(
            @ApiParam(name = "userId", value = "用户id", required = true)
            @RequestParamRequired @RequestParam String userId) {
       return R.ok(centerOrdersService.getOrderCount(userId));
    }

    /**
     * 根据用户id查询订单流向信息
     * @param userId 用户id
     * @param page 页码
     * @param pageSize 每页数量
     * @return 订单流向信息
     */
    @PostMapping("/trend")
    @ApiOperation(value = "查询订单流向", httpMethod = "POST", notes = "根据用户id查询订单流向信息")
    public R trend(
            @ApiParam(name = "userId", value = "用户ID", required = true)
            @RequestParamRequired @RequestParam String userId,
            @ApiParam(name = "page", value = "页码")
            @RequestParam(required = false) String page,
            @ApiParam(name = "pageSize", value = "每页数量")
            @RequestParam(required = false) String pageSize) {
        Map<String, Object> paramsMap = PageMap.getInstance(page, pageSize)
                .put("userId", userId)
                .getParamsMap();
        return R.ok(centerOrdersService.getOrderTrend(paramsMap));
    }
}
