package com.kang.controller;

import com.kang.BO.AddressBO;
import com.kang.common.annotation.RequestParamRequired;
import com.kang.common.utils.R;
import com.kang.common.validator.ValidatorUtils;
import com.kang.common.validator.group.AddressAddGroup;
import com.kang.common.validator.group.AddressUpdateGroup;
import com.kang.pojo.UserAddressEntity;
import com.kang.service.UserAddressService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * 收货地址相关controller
 * @author kang
 * @email 784706982@qq.com
 * @date 2021-04-24 10:10:59
 */
@RestController
@Api(value = "地址相关", tags = {"地址相关接口"})
@RequestMapping("address")
public class AddressController {

    @Autowired
    private UserAddressService userAddressService;

    /**
     * 根据用户id查询收货地址
     * @param userId 用户id
     * @return 收货地址
     */
    @ApiOperation(value = "查询收货地址", notes = "根据用户id查询收货地址", httpMethod = "POST")
    @PostMapping("/list")
    public R list(
            @ApiParam(name = "userId", value = "用户id", required = true)
            @RequestParamRequired @RequestParam String userId) {
        return R.ok(
                userAddressService.lambdaQuery()
                .eq(UserAddressEntity::getUserId, userId)
                .list());
    }


    /**
     * 用户新增收货地址
     * @param addressInfo 收货地址
     * @return 成功信息
     */
    @ApiOperation(value = "新增收货地址", notes = "用户新增收货地址", httpMethod = "POST")
    @PostMapping("/add")
    public R list(
            @ApiParam(name = "addressInfo", value = "收货地址信息", required = true)
            @RequestBody AddressBO addressInfo) {
        ValidatorUtils.validateEntity(addressInfo, AddressAddGroup.class);
        userAddressService.addNewAddress(addressInfo);
        return R.ok();
    }

    /**
     * 用户修改收货地址
     * @param addressInfo 收货地址
     * @return 成功信息
     */
    @ApiOperation(value = "修改收货地址", notes = "用户修改收货地址", httpMethod = "POST")
    @PostMapping("/update")
    public R update(
            @ApiParam(name = "addressInfo", value = "收货地址信息", required = true)
            @RequestBody AddressBO addressInfo) {
        ValidatorUtils.validateEntity(addressInfo, AddressUpdateGroup.class);
        userAddressService.updateAddress(addressInfo);
        return R.ok();
    }

    /**
     * 用户删除收货地址
     * @param userId 用户id
     * @param addressId 收货地址id
     * @return 成功信息
     */
    @ApiOperation(value = "删除收货地址", notes = "用户删除收货地址", httpMethod = "POST")
    @PostMapping("/delete")
    public R delete(
            @ApiParam(name = "userId", value = "用户id", required = true)
            @RequestParamRequired @RequestParam("userId")String userId,
            @ApiParam(name = "addressId", value = "收货地址id", required = true)
            @RequestParamRequired @RequestParam("addressId")String addressId) {
        userAddressService.lambdaUpdate()
                .eq(UserAddressEntity::getUserId, userId)
                .eq(UserAddressEntity::getId, addressId)
                .remove();
        return R.ok();
    }



    /**
     * 用户设置默认收货地址
     * @param userId 用户id
     * @param addressId 收货地址id
     * @return 成功信息
     */
    @ApiOperation(value = "设置默认收货地址", notes = "用户设置默认收货地址", httpMethod = "POST")
    @PostMapping("/setDefalut")
    public R setDefalut(
            @ApiParam(name = "userId", value = "用户id", required = true)
            @RequestParamRequired @RequestParam("userId")String userId,
            @ApiParam(name = "addressId", value = "收货地址id", required = true)
            @RequestParamRequired @RequestParam("addressId")String addressId) {
        userAddressService.setDefalutAddress(userId, addressId);
        return R.ok();
    }
}
