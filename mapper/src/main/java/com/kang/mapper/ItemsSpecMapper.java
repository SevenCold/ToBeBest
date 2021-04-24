package com.kang.mapper;

import com.kang.pojo.ItemsSpecEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 商品规格 每一件商品都有不同的规格，不同的规格又有不同的价格和优惠力度，规格表为此设计
 * 
 * @author kang
 * @email 784706982@qq.com
 * @date 2021-04-24 10:11:01
 */
@Mapper
public interface ItemsSpecMapper extends BaseMapper<ItemsSpecEntity> {
	
}
