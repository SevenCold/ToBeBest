package com.kang.pojo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.math.BigDecimal;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 商品规格 每一件商品都有不同的规格，不同的规格又有不同的价格和优惠力度，规格表为此设计
 * 
 * @author kang
 * @email 784706982@qq.com
 * @date 2021-04-24 10:11:01
 */
@Data
@TableName("items_spec")
public class ItemsSpecEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 商品规格id
	 */
	@TableId
	private String id;
	/**
	 * 商品外键id
	 */
	private String itemId;
	/**
	 * 规格名称
	 */
	private String name;
	/**
	 * 库存
	 */
	private Integer stock;
	/**
	 * 折扣力度
	 */
	private BigDecimal discounts;
	/**
	 * 优惠价
	 */
	private Integer priceDiscount;
	/**
	 * 原价
	 */
	private Integer priceNormal;
	/**
	 * 创建时间
	 */
	private Date createdTime;
	/**
	 * 更新时间
	 */
	private Date updatedTime;

}
