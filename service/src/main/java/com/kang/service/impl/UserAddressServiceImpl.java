package com.kang.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kang.common.utils.PageUtils;
import com.kang.common.utils.Query;

import com.kang.mapper.UserAddressMapper;
import com.kang.pojo.UserAddressEntity;
import com.kang.service.UserAddressService;


@Service("userAddressService")
public class UserAddressServiceImpl extends ServiceImpl<UserAddressMapper, UserAddressEntity> implements UserAddressService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<UserAddressEntity> page = this.page(
                new Query<UserAddressEntity>().getPage(params),
                new QueryWrapper<UserAddressEntity>()
        );

        return new PageUtils(page);
    }

}