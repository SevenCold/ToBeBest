package com.kang.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kang.common.utils.PageUtils;
import com.kang.common.utils.Query;
import com.kang.mapper.OrderStatusMapper;
import com.kang.pojo.OrderStatusEntity;
import com.kang.service.OrderStatusService;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service("orderStatusService")
public class OrderStatusServiceImpl extends ServiceImpl<OrderStatusMapper, OrderStatusEntity> implements OrderStatusService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<OrderStatusEntity> page = this.page(
                new Query<OrderStatusEntity>().getPage(params),
                new QueryWrapper<>()
        );

        return new PageUtils(page);
    }

}