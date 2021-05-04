package com.kang.service.center;

import com.baomidou.mybatisplus.extension.service.IService;
import com.kang.VO.center.CenterOrdersVO;
import com.kang.common.utils.PageUtils;

import java.util.Map;

/**
 * 订单表 
 *
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
}

