package com.kang.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.kang.common.utils.PageUtils;
import com.kang.pojo.CarouselEntity;

import java.util.Map;

/**
 * 轮播图 
 *
 * @author kang
 * @email 784706982@qq.com
 * @date 2021-04-24 10:11:03
 */
public interface CarouselService extends IService<CarouselEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

