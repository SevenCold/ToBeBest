package com.kang.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.kang.common.utils.PageUtils;
import com.kang.pojo.ItemsEntity;

import java.util.Map;

/**
 * 商品表 商品信息相关表：分类表，商品图片表，商品规格表，商品参数表
 *
 * @author kang
 * @email 784706982@qq.com
 * @date 2021-04-24 10:11:01
 */
public interface ItemsService extends IService<ItemsEntity> {

    PageUtils queryItemComments(Map<String, Object> params);
}

