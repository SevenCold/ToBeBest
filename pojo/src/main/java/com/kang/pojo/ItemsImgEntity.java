package com.kang.pojo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 商品图片 
 * 
 * @author kang
 * @email 784706982@qq.com
 * @date 2021-04-24 10:11:02
 */
@Data
@TableName("items_img")
public class ItemsImgEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 图片主键
	 */
	@TableId
	private String id;
	/**
	 * 商品外键id 商品外键id
	 */
	private String itemId;
	/**
	 * 图片地址 图片地址
	 */
	private String url;
	/**
	 * 顺序 图片顺序，从小到大
	 */
	private Integer sort;
	/**
	 * 是否主图 是否主图，1：是，0：否
	 */
	private Integer isMain;
	/**
	 * 创建时间
	 */
	private Date createdTime;
	/**
	 * 更新时间
	 */
	private Date updatedTime;

}
