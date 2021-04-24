package com.kang.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kang.common.utils.PageUtils;
import com.kang.common.utils.Query;

import com.kang.mapper.ItemsMapper;
import com.kang.pojo.ItemsEntity;
import com.kang.service.ItemsService;


@Service("itemsService")
public class ItemsServiceImpl extends ServiceImpl<ItemsMapper, ItemsEntity> implements ItemsService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<ItemsEntity> page = this.page(
                new Query<ItemsEntity>().getPage(params),
                new QueryWrapper<ItemsEntity>()
        );

        return new PageUtils(page);
    }

}