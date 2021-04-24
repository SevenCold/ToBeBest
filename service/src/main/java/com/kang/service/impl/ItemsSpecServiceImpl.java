package com.kang.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kang.common.utils.PageUtils;
import com.kang.common.utils.Query;

import com.kang.mapper.ItemsSpecMapper;
import com.kang.pojo.ItemsSpecEntity;
import com.kang.service.ItemsSpecService;


@Service("itemsSpecService")
public class ItemsSpecServiceImpl extends ServiceImpl<ItemsSpecMapper, ItemsSpecEntity> implements ItemsSpecService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<ItemsSpecEntity> page = this.page(
                new Query<ItemsSpecEntity>().getPage(params),
                new QueryWrapper<ItemsSpecEntity>()
        );

        return new PageUtils(page);
    }

}