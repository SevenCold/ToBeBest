package com.kang.controller.center;

import com.kang.BO.center.OrderItemsCommentBO;
import com.kang.common.annotation.RequestParamRequired;
import com.kang.common.enums.ErrorMsgEnum;
import com.kang.common.enums.YesNoEnum;
import com.kang.common.utils.PageMap;
import com.kang.common.utils.R;
import com.kang.common.validator.ValidatorUtils;
import com.kang.common.validator.group.CenterCommentGroup;
import com.kang.pojo.OrderItemsEntity;
import com.kang.pojo.OrdersEntity;
import com.kang.service.OrderItemsService;
import com.kang.service.OrdersService;
import com.kang.service.center.CenterCommentsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/** 个人中心 我的评价接口
 * @author 汪少
 * @date 2021/5/5 9:58
 */
@Api(value = "个人中心-我的评价", tags = "CENTER-我的评价接口")
@RestController
@RequestMapping("mycomments")
public class CenterCommentController {

    @Autowired
    private OrderItemsService orderItemsService;

    @Autowired
    private OrdersService ordersService;

    @Autowired
    private CenterCommentsService commentsService;

    /**
     * 查询评论所需订单信息
     * @param orderId 订单id
     * @param userId 用户id
     * @return 订单信息
     */
    @PostMapping("pending")
    @ApiOperation(value = "查询评论所需订单信息", httpMethod = "POST", notes = "查询评论所需订单信息")
    public R pending(
            @ApiParam(name = "orderId", value = "订单ID", required = true)
            @RequestParamRequired @RequestParam String orderId,
            @ApiParam(name = "userId", value = "用户id", required = true)
            @RequestParamRequired @RequestParam String userId) {
        OrdersEntity order = ordersService.getOrderByUserId(orderId, userId);
        // 订单不存在 或 已被评价过
        if (order == null || YesNoEnum.YES.getCode().equals(order.getIsComment())) {
            return ErrorMsgEnum.ORDER_NOT_EXIST.getR();
        }
        return R.ok(orderItemsService.lambdaQuery()
                        .eq(OrderItemsEntity::getOrderId, orderId)
                        .list());
    }

    /**
     * 用户评论
     * @param orderId 订单id
     * @param userId 用户id
     * @return 订单信息
     */
    @PostMapping("saveList")
    @ApiOperation(value = "用户评论", httpMethod = "POST", notes = "用户评论")
    public R saveList(
            @ApiParam(name = "orderId", value = "订单ID", required = true)
            @RequestParamRequired @RequestParam String orderId,
            @ApiParam(name = "userId", value = "用户id", required = true)
            @RequestParamRequired @RequestParam String userId,
            @RequestBody List<OrderItemsCommentBO> boList) {
        ValidatorUtils.validateList(boList, CenterCommentGroup.class);
        OrdersEntity order = ordersService.getOrderByUserId(orderId, userId);
        // 订单不存在 或 已被评价过
        if (order == null || YesNoEnum.YES.getCode().equals(order.getIsComment())) {
            return ErrorMsgEnum.ORDER_NOT_EXIST.getR();
        }
        ordersService.saveComment(orderId, userId, boList);
        return R.ok();
    }

    /**
     * 根据用户id查询我的评价信息
     * @param userId 用户id
     * @param page 页码
     * @param pageSize 每页数量
     * @return 我的评价信息
     */
    @PostMapping("query")
    @ApiOperation(value = "个人中心-我的评价", httpMethod = "POST", notes = "个人中心-我的评价")
    public R query(
            @ApiParam(name = "userId", value = "用户ID", required = true)
            @RequestParamRequired @RequestParam String userId,
            @ApiParam(name = "page", value = "页码")
            @RequestParam(required = false) String page,
            @ApiParam(name = "pageSize", value = "每页数量")
            @RequestParam(required = false) String pageSize) {
        Map<String, Object> paramsMap = PageMap.getInstance(page, pageSize)
                .put("userId", userId)
                .getParamsMap();
        return R.ok(commentsService.queryMyComments(paramsMap));
    }
}
