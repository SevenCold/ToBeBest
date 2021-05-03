package com.kang.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kang.common.utils.PageUtils;
import com.kang.common.utils.Query;
import com.kang.mapper.OrderItemsMapper;
import com.kang.pojo.OrderItemsEntity;
import com.kang.service.OrderItemsService;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service("orderItemsService")
public class OrderItemsServiceImpl extends ServiceImpl<OrderItemsMapper, OrderItemsEntity> implements OrderItemsService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<OrderItemsEntity> page = this.page(
                new Query<OrderItemsEntity>().getPage(params),
                new QueryWrapper<>()
        );

        return new PageUtils(page);
    }

}