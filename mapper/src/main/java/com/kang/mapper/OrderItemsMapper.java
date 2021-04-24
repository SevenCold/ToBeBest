package com.kang.mapper;

import com.kang.pojo.OrderItemsEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 订单商品关联表 
 * 
 * @author kang
 * @email 784706982@qq.com
 * @date 2021-04-24 10:11:00
 */
@Mapper
public interface OrderItemsMapper extends BaseMapper<OrderItemsEntity> {
	
}
