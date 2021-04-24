package com.kang.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.kang.common.utils.PageUtils;
import com.kang.pojo.ItemsCommentsEntity;

import java.util.Map;

/**
 * 商品评价表 
 *
 * @author kang
 * @email 784706982@qq.com
 * @date 2021-04-24 10:11:00
 */
public interface ItemsCommentsService extends IService<ItemsCommentsEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

