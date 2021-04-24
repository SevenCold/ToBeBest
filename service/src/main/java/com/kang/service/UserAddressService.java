package com.kang.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.kang.common.utils.PageUtils;
import com.kang.pojo.UserAddressEntity;

import java.util.Map;

/**
 * 用户地址表 
 *
 * @author kang
 * @email 784706982@qq.com
 * @date 2021-04-24 10:11:02
 */
public interface UserAddressService extends IService<UserAddressEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

