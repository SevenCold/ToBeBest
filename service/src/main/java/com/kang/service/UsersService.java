package com.kang.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.kang.BO.UserBO;
import com.kang.common.utils.PageUtils;
import com.kang.pojo.UsersEntity;

import java.util.Map;

/**
 * 用户表 
 *
 * @author kang
 * @email 784706982@qq.com
 * @date 2021-04-24 10:11:02
 */
public interface UsersService extends IService<UsersEntity> {

    PageUtils queryPage(Map<String, Object> params);

    /**
     * 根据用户名称 查询是否存在用户
     * @param userName 用户名称
     * @return 是否存在
     */
    boolean queryUserNameExist(String userName);

    /**
     * 插入用户信息
     * @param userBO 用户信息
     */
    UsersEntity insertUser(UserBO userBO);

    /**
     * 用户登录
     * @param userName 用户名称
     * @param password 密码：明文
     */
    UsersEntity queryUserLogin(String userName, String password);
}

