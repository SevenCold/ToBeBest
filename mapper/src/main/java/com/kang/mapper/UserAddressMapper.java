package com.kang.mapper;

import com.kang.pojo.UserAddressEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 用户地址表 
 * 
 * @author kang
 * @email 784706982@qq.com
 * @date 2021-04-24 10:11:02
 */
@Mapper
public interface UserAddressMapper extends BaseMapper<UserAddressEntity> {
	
}
