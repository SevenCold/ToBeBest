package com.kang.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.kang.common.utils.PageUtils;
import com.kang.pojo.CategoryEntity;

import java.util.Map;

/**
 * 商品分类 
 *
 * @author kang
 * @email 784706982@qq.com
 * @date 2021-04-24 10:11:02
 */
public interface CategoryService extends IService<CategoryEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

