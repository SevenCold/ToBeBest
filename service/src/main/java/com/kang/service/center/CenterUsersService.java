package com.kang.service.center;

import com.baomidou.mybatisplus.extension.service.IService;
import com.kang.BO.center.CenterUserBO;
import com.kang.pojo.UsersEntity;

/**
 * 个人中心 用户查询
 * @author kang
 * @email 784706982@qq.com
 * @date 2021-04-24 10:11:02
 */
public interface CenterUsersService extends IService<UsersEntity> {
    /**
     * 根据用户id查询用户信息
     * @param userId 用户id
     */
    UsersEntity queryUserInfo(String userId);

    /**
     * 根据用户id 修改 用户信息
     * @param userId 用户id
     * @param userBO 用户信息
     * @return 修改后的用户信息
     */
    UsersEntity updateUserInfo(String userId, CenterUserBO userBO);
}

