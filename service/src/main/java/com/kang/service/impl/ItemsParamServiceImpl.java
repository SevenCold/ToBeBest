package com.kang.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kang.common.utils.PageUtils;
import com.kang.common.utils.Query;

import com.kang.mapper.ItemsParamMapper;
import com.kang.pojo.ItemsParamEntity;
import com.kang.service.ItemsParamService;


@Service("itemsParamService")
public class ItemsParamServiceImpl extends ServiceImpl<ItemsParamMapper, ItemsParamEntity> implements ItemsParamService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<ItemsParamEntity> page = this.page(
                new Query<ItemsParamEntity>().getPage(params),
                new QueryWrapper<ItemsParamEntity>()
        );

        return new PageUtils(page);
    }

}