package com.kang.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kang.common.utils.PageUtils;
import com.kang.common.utils.Query;

import com.kang.mapper.ItemsCommentsMapper;
import com.kang.pojo.ItemsCommentsEntity;
import com.kang.service.ItemsCommentsService;


@Service("itemsCommentsService")
public class ItemsCommentsServiceImpl extends ServiceImpl<ItemsCommentsMapper, ItemsCommentsEntity> implements ItemsCommentsService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<ItemsCommentsEntity> page = this.page(
                new Query<ItemsCommentsEntity>().getPage(params),
                new QueryWrapper<ItemsCommentsEntity>()
        );

        return new PageUtils(page);
    }

}