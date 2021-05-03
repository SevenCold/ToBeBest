package com.kang.BO;

import com.kang.common.validator.group.SubmitOrderGroup;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * 用于创建订单的BO对象
 */
@Data
public class SubmitOrderBO {

    @NotBlank(message = "用户信息不存在", groups = {SubmitOrderGroup.class})
    @ApiModelProperty(notes = "用户id", required = true, example = "190825CG3AA14Y3C")
    private String userId;

    @NotBlank(message = "商品信息不存在", groups = {SubmitOrderGroup.class})
    @ApiModelProperty(notes = "多个规格id", required = true, example = "1,2,3")
    private String itemSpecIds;

    @NotBlank(message = "收货地址信息不存在", groups = {SubmitOrderGroup.class})
    @ApiModelProperty(notes = "收货地址id", required = true, example = "190825CG3AA14Y3C")
    private String addressId;

    @NotNull(message = "支付方式不存在", groups = {SubmitOrderGroup.class})
    @Range(max = 2, min = 1, message = "支付方式不支持", groups = {SubmitOrderGroup.class})
    @ApiModelProperty(notes = "支付方式（1为微信，2为支付宝）", required = true, example = "1")
    private Integer payMethod;

    @ApiModelProperty(notes = "买家备注")
    @Length(max = 100, message = "备注信息不能超过100字", groups = {SubmitOrderGroup.class})
    private String leftMsg;
}
