package com.kang.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.kang.common.utils.PageUtils;
import com.kang.pojo.ItemsImgEntity;

import java.util.Map;

/**
 * 商品图片 
 *
 * @author kang
 * @email 784706982@qq.com
 * @date 2021-04-24 10:11:02
 */
public interface ItemsImgService extends IService<ItemsImgEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

