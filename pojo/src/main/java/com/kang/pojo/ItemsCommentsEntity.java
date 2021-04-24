package com.kang.pojo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 商品评价表 
 * 
 * @author kang
 * @email 784706982@qq.com
 * @date 2021-04-24 10:11:00
 */
@Data
@TableName("items_comments")
public class ItemsCommentsEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * id主键
	 */
	@TableId
	private String id;
	/**
	 * 用户id 用户名须脱敏
	 */
	private String userId;
	/**
	 * 商品id
	 */
	private String itemId;
	/**
	 * 商品名称
	 */
	private String itemName;
	/**
	 * 商品规格id 可为空
	 */
	private String itemSpecId;
	/**
	 * 规格名称 可为空
	 */
	private String sepcName;
	/**
	 * 评价等级 1：好评 2：中评 3：差评
	 */
	private Integer commentLevel;
	/**
	 * 评价内容
	 */
	private String content;
	/**
	 * 创建时间
	 */
	private Date createdTime;
	/**
	 * 更新时间
	 */
	private Date updatedTime;

}
