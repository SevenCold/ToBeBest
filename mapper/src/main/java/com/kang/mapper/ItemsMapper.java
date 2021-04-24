package com.kang.mapper;

import com.kang.pojo.ItemsEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 商品表 商品信息相关表：分类表，商品图片表，商品规格表，商品参数表
 * 
 * @author kang
 * @email 784706982@qq.com
 * @date 2021-04-24 10:11:01
 */
@Mapper
public interface ItemsMapper extends BaseMapper<ItemsEntity> {
	
}
