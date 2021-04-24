package com.kang.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.kang.common.utils.PageUtils;
import com.kang.pojo.OrderStatusEntity;

import java.util.Map;

/**
 * 订单状态表 订单的每个状态更改都需要进行记录
10：待付款  20：已付款，待发货  30：已发货，待收货（7天自动确认）  40：交易成功（此时可以评价）50：交易关闭（待付款时，用户取消 或 长时间未付款，系统识别后自动关闭）
退货/退货，此分支流程不做，所以不加入
 *
 * @author kang
 * @email 784706982@qq.com
 * @date 2021-04-24 10:10:59
 */
public interface OrderStatusService extends IService<OrderStatusEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

