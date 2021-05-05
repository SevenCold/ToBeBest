package com.kang.service.impl.center;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kang.VO.center.CenterOrdersVO;
import com.kang.VO.center.OrderStatusCountsVO;
import com.kang.common.enums.OrderStatusEnum;
import com.kang.common.enums.YesNoEnum;
import com.kang.common.utils.PageUtils;
import com.kang.common.utils.Query;
import com.kang.mapper.center.CenterOrdersMapper;
import com.kang.pojo.OrderStatusEntity;
import com.kang.service.center.CenterOrdersService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service("centerOrdersService")
public class CenterOrdersServiceImpl extends ServiceImpl<CenterOrdersMapper, CenterOrdersVO> implements CenterOrdersService {

    @Override
    public PageUtils queryMyOrders(Map<String, Object> map) {
        IPage<CenterOrdersVO> page = new Query<CenterOrdersVO>().getPage(map);
        List<CenterOrdersVO> ordersVO = this.baseMapper.queryMyOrders(page, map);
        return new PageUtils(page, ordersVO);
    }

    @Override
    public OrderStatusCountsVO getOrderCount(String userId) {
        Map<String, Object> map = new HashMap<>();
        map.put("userId", userId);

        map.put("orderStatus", OrderStatusEnum.WAIT_PAY.getValue());
        int waitPayCounts = this.baseMapper.getOrdersCountByStatus(map);

        map.put("orderStatus", OrderStatusEnum.WAIT_DELIVER.getValue());
        int waitDeliverCounts = this.baseMapper.getOrdersCountByStatus(map);

        map.put("orderStatus", OrderStatusEnum.WAIT_RECEIVE.getValue());
        int waitReceiveCounts = this.baseMapper.getOrdersCountByStatus(map);

        map.put("orderStatus", OrderStatusEnum.SUCCESS.getValue());
        map.put("isComment", YesNoEnum.NO.getCode());
        int waitCommentCounts = this.baseMapper.getOrdersCountByStatus(map);

        return new OrderStatusCountsVO(waitPayCounts, waitDeliverCounts, waitReceiveCounts, waitCommentCounts);
    }

    @Override
    public PageUtils getOrderTrend(Map<String, Object> map) {
        IPage<OrderStatusEntity> page = new Query<OrderStatusEntity>().getPage(map);
        List<OrderStatusEntity> orderTrend = this.baseMapper.getOrderTrend(page, map);
        return new PageUtils(page, orderTrend);
    }
}