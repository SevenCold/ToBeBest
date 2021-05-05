package com.kang.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.kang.BO.SubmitOrderBO;
import com.kang.BO.center.OrderItemsCommentBO;
import com.kang.pojo.OrdersEntity;

import java.util.List;

/**
 * 订单表 
 *
 * @author kang
 * @email 784706982@qq.com
 * @date 2021-04-24 10:11:00
 */
public interface OrdersService extends IService<OrdersEntity> {

    /**
     * 创建订单
     * @param orderInfo 订单信息
     */
    String createOrder(SubmitOrderBO orderInfo);

    /**
     * 通过用户id查询没有被删除的订单信息
     * @param orderId 订单id
     * @param userId 用户id
     * @return 订单信息
     */
    OrdersEntity getOrderByUserId(String orderId, String userId);

    /**
     * 保存订单的评论
     * @param orderId 订单id
     * @param userId 用户id
     * @param boList 表单内容
     */
    void saveComment(String orderId, String userId, List<OrderItemsCommentBO> boList);
}

