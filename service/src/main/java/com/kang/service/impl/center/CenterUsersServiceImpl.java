package com.kang.service.impl.center;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kang.BO.center.CenterUserBO;
import com.kang.mapper.UsersMapper;
import com.kang.pojo.UsersEntity;
import com.kang.service.center.CenterUsersService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.Date;


@Service("centerUsersService")
public class CenterUsersServiceImpl extends ServiceImpl<UsersMapper, UsersEntity> implements CenterUsersService {

    @Override
    public UsersEntity queryUserInfo(String userId) {
        UsersEntity user = this.lambdaQuery().eq(UsersEntity::getId, userId).one();
        user.setPassword(null);
        return user;
    }

    @Override
    public UsersEntity updateUserInfo(String userId, CenterUserBO userBO) {
        UsersEntity user = new UsersEntity();
        BeanUtils.copyProperties(userBO, user);
        user.setId(userId);
        user.setUpdatedTime(new Date());
        this.updateById(user);
        return queryUserInfo(userId);
    }
}