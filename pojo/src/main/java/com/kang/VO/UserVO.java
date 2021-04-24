package com.kang.VO;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户信息VO，返回前端
 */
@Data
public class UserVO implements Serializable {
    private static final long serialVersionUID = 1L;

    private String id;
    /**
     * 用户名 用户名
     */
    private String username;
    /**
     * 昵称 昵称
     */
    private String nickname;
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
}
