package com.kang.mapper.center;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.kang.VO.center.CenterOrdersVO;
import com.kang.pojo.OrderStatusEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 个人中心 订单管理
 * @author kang
 * @email 784706982@qq.com
 * @date 2021-04-24 10:11:00
 */
@Mapper
public interface CenterOrdersMapper extends BaseMapper<CenterOrdersVO> {

    /**
     * 根据订单状态 查询订单信息
     * @param page 分页数据
     * @param map 查询条件
     * @return 订单信息
     */
    List<CenterOrdersVO> queryMyOrders(IPage<CenterOrdersVO> page, @Param("paramsMap") Map<String, Object> map);

    /**
     * 通过订单状态和userId获取订单个数
     * @param map 查询条件
     * @return 订单个数
     */
    int getOrdersCountByStatus(@Param("paramsMap") Map<String, Object> map);

    /**
     * 通过userId查询订单状态信息
     * @param map 查询条件
     * @return 订单状态信息
     */
    List<OrderStatusEntity> getOrderTrend(IPage<OrderStatusEntity> page, @Param("paramsMap") Map<String, Object> map);
}
