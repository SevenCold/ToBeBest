package com.kang.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kang.BO.AddressBO;
import com.kang.common.enums.YesNoEnum;
import com.kang.common.org.n3r.idworker.Sid;
import com.kang.common.utils.PageUtils;
import com.kang.common.utils.Query;
import com.kang.mapper.UserAddressMapper;
import com.kang.pojo.UserAddressEntity;
import com.kang.service.UserAddressService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.Date;
import java.util.List;
import java.util.Map;


@Service("userAddressService")
public class UserAddressServiceImpl extends ServiceImpl<UserAddressMapper, UserAddressEntity> implements UserAddressService {

    @Autowired
    private Sid sid;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<UserAddressEntity> page = this.page(
                new Query<UserAddressEntity>().getPage(params),
                new QueryWrapper<>()
        );

        return new PageUtils(page);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void addNewAddress(AddressBO addressInfo) {
        UserAddressEntity addressEntity = new UserAddressEntity();
        BeanUtils.copyProperties(addressInfo, addressEntity);
        // 1.判断用户是否有收货地址， 若没有需要设为默认地址
        List<UserAddressEntity> existedAddressList = this.lambdaQuery().eq(UserAddressEntity::getUserId, addressInfo.getUserId())
                .list();
        addressEntity.setIsDefault(CollectionUtils.isEmpty(existedAddressList)
                        ? YesNoEnum.YES.getCode() : YesNoEnum.NO.getCode());
        addressEntity.setId(sid.nextShort());
        Date now = new Date();
        addressEntity.setCreatedTime(now);
        addressEntity.setUpdatedTime(now);
        this.save(addressEntity);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void updateAddress(AddressBO addressInfo) {
        UserAddressEntity addressEntity = new UserAddressEntity();
        BeanUtils.copyProperties(addressInfo, addressEntity);
        addressEntity.setId(addressInfo.getAddressId());
        addressEntity.setUpdatedTime(new Date());
        this.updateById(addressEntity);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void setDefalutAddress(String userId, String addressId) {
        // 1.将原来的默认取消
        List<UserAddressEntity> existedList = this.lambdaQuery().eq(UserAddressEntity::getIsDefault, YesNoEnum.YES.getCode())
                .eq(UserAddressEntity::getUserId, userId)
                .list();
        if (!CollectionUtils.isEmpty(existedList)) {
            existedList.forEach(e -> this.lambdaUpdate().eq(UserAddressEntity::getId, e.getId())
                    .set(UserAddressEntity::getIsDefault, YesNoEnum.NO.getCode())
                    .update());
        }
        // 2.设置默认地址
        this.lambdaUpdate().eq(UserAddressEntity::getId, addressId)
                .set(UserAddressEntity::getIsDefault, YesNoEnum.YES.getCode())
                .update();
    }

}