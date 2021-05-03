package com.kang.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.kang.BO.SubmitOrderBO;
import com.kang.pojo.OrdersEntity;

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
}

