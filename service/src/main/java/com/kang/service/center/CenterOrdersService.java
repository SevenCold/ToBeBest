package com.kang.service.center;

import com.baomidou.mybatisplus.extension.service.IService;
import com.kang.VO.center.CenterOrdersVO;
import com.kang.VO.center.OrderStatusCountsVO;
import com.kang.common.utils.PageUtils;

import java.util.Map;

/**
 * 个人中心 我的订单
 * @author kang
 * @email 784706982@qq.com
 * @date 2021-04-24 10:11:00
 */
public interface CenterOrdersService extends IService<CenterOrdersVO> {

    /**
     * 根据订单状态 查询订单信息
     * @param map 查询条件
     * @return 分页的订单信息
     */
    PageUtils queryMyOrders(Map<String, Object> map);

    /**
     * 通过用户id获取不同状态的订单个数
     * @param userId 用户id
     * @return 个数对象
     */
    OrderStatusCountsVO getOrderCount(String userId);

    /**
     * 通过userId查询订单状态信息
     * @param map 查询条件
     * @return 分页的订单状态信息
     */
    PageUtils getOrderTrend(Map<String, Object> map);
}

