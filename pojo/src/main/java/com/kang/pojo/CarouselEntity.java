package com.kang.pojo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 轮播图 
 * 
 * @author kang
 * @email 784706982@qq.com
 * @date 2021-04-24 10:11:03
 */
@Data
@TableName("carousel")
public class CarouselEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	@TableId
	private String id;
	/**
	 * 图片 图片地址
	 */
	private String imageUrl;
	/**
	 * 背景色 背景颜色
	 */
	private String backgroundColor;
	/**
	 * 商品id 商品id
	 */
	private String itemId;
	/**
	 * 商品分类id 商品分类id
	 */
	private String catId;
	/**
	 * 轮播图类型 轮播图类型，用于判断，可以根据商品id或者分类进行页面跳转，1：商品 2：分类
	 */
	private Integer type;
	/**
	 * 轮播图展示顺序 轮播图展示顺序，从小到大
	 */
	private Integer sort;
	/**
	 * 是否展示 是否展示，1：展示    0：不展示
	 */
	private Integer isShow;
	/**
	 * 创建时间 创建时间
	 */
	private Date createTime;
	/**
	 * 更新时间 更新
	 */
	private Date updateTime;

}
