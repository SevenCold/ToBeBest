package com.kang.pojo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户地址表 
 * 
 * @author kang
 * @email 784706982@qq.com
 * @date 2021-04-24 10:11:02
 */
@Data
@TableName("user_address")
public class UserAddressEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 地址主键id
	 */
	@TableId
	private String id;
	/**
	 * 关联用户id
	 */
	private String userId;
	/**
	 * 收件人姓名
	 */
	private String receiver;
	/**
	 * 收件人手机号
	 */
	private String mobile;
	/**
	 * 省份
	 */
	private String province;
	/**
	 * 城市
	 */
	private String city;
	/**
	 * 区县
	 */
	private String district;
	/**
	 * 详细地址
	 */
	private String detail;
	/**
	 * 扩展字段
	 */
	private String extand;
	/**
	 * 是否默认地址 1:是  0:否
	 */
	private Integer isDefault;
	/**
	 * 创建时间
	 */
	private Date createdTime;
	/**
	 * 更新时间
	 */
	private Date updatedTime;

	/**
	 * 获取具体的收货地址
	 * @return 收货地址
	 */
	public String getAddress() {
		return this.province + " " + this.city + " " + this.district + " " + this.detail;
	}
}
