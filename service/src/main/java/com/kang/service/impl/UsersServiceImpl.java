package com.kang.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kang.BO.UserBO;
import com.kang.common.enums.SexTypeEnum;
import com.kang.common.org.n3r.idworker.Sid;
import com.kang.common.utils.*;
import com.kang.mapper.UsersMapper;
import com.kang.pojo.UsersEntity;
import com.kang.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Map;


@Service("usersService")
public class UsersServiceImpl extends ServiceImpl<UsersMapper, UsersEntity> implements UsersService {

    @Autowired
    private Sid sid;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<UsersEntity> page = this.page(
                new Query<UsersEntity>().getPage(params),
                new QueryWrapper<>()
        );

        return new PageUtils(page);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public boolean queryUserNameExist(String userName) {
        QueryWrapper<UsersEntity> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(UsersEntity::getUsername, userName);
        return this.getOne(wrapper) != null;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public UsersEntity insertUser(UserBO userBO) {
        String userId = sid.nextShort();
        UsersEntity user = new UsersEntity();
        user.setId(userId);
        user.setUsername(userBO.getUsername());
        user.setPassword(MD5Utils.getMD5Str(userBO.getPassword()));
        // 默认用户昵称同用户名
        user.setNickname(userBO.getUsername());
        // 默认头像
        user.setFace(Constant.USER_FACE);
        // 默认生日
        user.setBirthday(DateUtil.stringToDate("1900-01-01"));
        // 默认性别为 保密
        user.setSex(SexTypeEnum.SECRET.getCode());

        user.setCreatedTime(new Date());
        user.setUpdatedTime(new Date());
        this.save(user);
        return user;
    }

    @Override
    public UsersEntity queryUserLogin(String userName, String password) {
        QueryWrapper<UsersEntity> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(UsersEntity::getUsername, userName)
                .eq(UsersEntity::getPassword, MD5Utils.getMD5Str(password));
        return this.getOne(wrapper);
    }

}