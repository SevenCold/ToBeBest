package com.kang.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kang.common.utils.PageUtils;
import com.kang.common.utils.Query;
import com.kang.mapper.CarouselMapper;
import com.kang.pojo.CarouselEntity;
import com.kang.service.CarouselService;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service("carouselService")
public class CarouselServiceImpl extends ServiceImpl<CarouselMapper, CarouselEntity> implements CarouselService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<CarouselEntity> page = this.page(
                new Query<CarouselEntity>().getPage(params),
                new QueryWrapper<>()
        );

        return new PageUtils(page);
    }

}