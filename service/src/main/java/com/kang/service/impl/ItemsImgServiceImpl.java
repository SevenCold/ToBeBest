package com.kang.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kang.common.utils.PageUtils;
import com.kang.common.utils.Query;

import com.kang.mapper.ItemsImgMapper;
import com.kang.pojo.ItemsImgEntity;
import com.kang.service.ItemsImgService;


@Service("itemsImgService")
public class ItemsImgServiceImpl extends ServiceImpl<ItemsImgMapper, ItemsImgEntity> implements ItemsImgService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<ItemsImgEntity> page = this.page(
                new Query<ItemsImgEntity>().getPage(params),
                new QueryWrapper<ItemsImgEntity>()
        );

        return new PageUtils(page);
    }

}