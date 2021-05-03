package com.kang.BO;

import com.kang.common.annotation.Mobile;
import com.kang.common.validator.group.AddressAddGroup;
import com.kang.common.validator.group.AddressUpdateGroup;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * 用户新增或修改地址的BO
 */
@Data
@ApiModel("用户添加收货地址表单")
public class AddressBO {

    @NotBlank(groups = {AddressUpdateGroup.class}, message = "收货信息错误")
    @ApiModelProperty(notes = "收货地址id", required = true, example = "190825CG3AA14Y3C")
    private String addressId;

    @ApiModelProperty(notes = "用户id", required = true, example = "1908189H7TNWDTXP")
    @NotNull(groups = {AddressAddGroup.class, AddressUpdateGroup.class}, message = "用户id不能位空")
    private String userId;

    @ApiModelProperty(notes = "收货人姓名", required = true, example = "张三")
    @NotBlank(groups = {AddressAddGroup.class, AddressUpdateGroup.class}, message = "收货人姓名不能为空")
    @Length(min = 2, max = 12, message = "收货人姓名不符合2-12位",
            groups = {AddressAddGroup.class, AddressUpdateGroup.class})
    private String receiver;

    @ApiModelProperty(notes = "收货人手机号", required = true, example = "13612345678")
    @Mobile(groups = {AddressAddGroup.class, AddressUpdateGroup.class})
    @NotBlank(groups = {AddressAddGroup.class, AddressUpdateGroup.class}, message = "收货人手机号不能为空")
    private String mobile;

    @ApiModelProperty(notes = "收货人所在省会", required = true, example = "北京")
    @NotBlank(groups = {AddressAddGroup.class, AddressUpdateGroup.class}, message = "收货人地址不正确")
    private String province;

    @ApiModelProperty(notes = "收货人所在城市", required = true, example = "北京")
    @NotBlank(groups = {AddressAddGroup.class, AddressUpdateGroup.class}, message = "收货人地址不正确")
    private String city;

    @ApiModelProperty(notes = "收货人所在市区", required = true, example = "石景山区")
    @NotBlank(groups = {AddressAddGroup.class, AddressUpdateGroup.class}, message = "收货人地址不正确")
    private String district;

    @ApiModelProperty(notes = "收货人详细地址", required = true, example = "石景山图书馆")
    @NotBlank(groups = {AddressAddGroup.class, AddressUpdateGroup.class}, message = "收货人地址不正确")
    private String detail;
}
