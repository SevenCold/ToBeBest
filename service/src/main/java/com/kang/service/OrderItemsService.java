package com.kang.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.kang.common.utils.PageUtils;
import com.kang.pojo.OrderItemsEntity;

import java.util.Map;

/**
 * 订单商品关联表 
 *
 * @author kang
 * @email 784706982@qq.com
 * @date 2021-04-24 10:11:00
 */
public interface OrderItemsService extends IService<OrderItemsEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

