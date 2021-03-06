package com.kang.pojo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 用户表 
 * 
 * @author kang
 * @email 784706982@qq.com
 * @date 2021-04-24 10:11:02
 */
@Data
@TableName("users")
public class UsersEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 主键id 用户id
	 */
	@TableId
	private String id;
	/**
	 * 用户名 用户名
	 */
	private String username;
	/**
	 * 密码 密码
	 */
	private String password;
	/**
	 * 昵称 昵称
	 */
	private String nickname;
	/**
	 * 真实姓名 真实姓名
	 */
	private String realname;
	/**
	 * 头像 头像
	 */
	private String face;
	/**
	 * 手机号 手机号
	 */
	private String mobile;
	/**
	 * 邮箱地址 邮箱地址
	 */
	private String email;
	/**
	 * 性别 性别 1:男  0:女  2:保密
	 */
	private Integer sex;
	/**
	 * 生日 生日
	 */
	private Date birthday;
	/**
	 * 创建时间 创建时间
	 */
	private Date createdTime;
	/**
	 * 更新时间 更新时间
	 */
	private Date updatedTime;

}
