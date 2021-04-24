package com.kang.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.kang.common.utils.PageUtils;
import com.kang.pojo.ItemsParamEntity;

import java.util.Map;

/**
 * 商品参数 
 *
 * @author kang
 * @email 784706982@qq.com
 * @date 2021-04-24 10:11:01
 */
public interface ItemsParamService extends IService<ItemsParamEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

