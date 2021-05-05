package com.kang.BO.center;

import com.kang.common.validator.group.CenterCommentGroup;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;

/**
 * 订单评论对象
 */
@Data
@ApiModel(value="订单评论对象", description="个人中心订单评论对象")
public class OrderItemsCommentBO {

    @ApiModelProperty(value="订单id", name="commentId", example="1")
    private String commentId;

    @ApiModelProperty(value="商品id", name="itemId", example="cake-1005")
    @NotNull(message = "商品信息不存在", groups = {CenterCommentGroup.class})
    @Length(min = 1, message = "商品信息不存在", groups = {CenterCommentGroup.class})
    private String itemId;

    @ApiModelProperty(value="商品名称", name="itemName", example="【天天吃货】进口美食凤梨酥")
    private String itemName;

    @ApiModelProperty(value="商品规格id", name="itemSpecId", example="cake-1005-spec-1")
    private String itemSpecId;

    @ApiModelProperty(value="商品规格名称", name="itemSpecName", example="巧克力")
    private String itemSpecName;

    @ApiModelProperty(value="评价等级 1：好评 2：中评 3：差评", name="commentLevel", example="1")
    @NotNull(message = "评论等级不能为空", groups = {CenterCommentGroup.class})
    @Range(min = 1, max = 3, message = "评价等级错误", groups = {CenterCommentGroup.class})
    private Integer commentLevel;

    @ApiModelProperty(value="评价内容", name="content", example="很好吃")
    @NotNull(message = "评论内容不能为空", groups = {CenterCommentGroup.class})
    @Length(max = 100, min = 1, message = "评价内容不符合1-100字", groups = {CenterCommentGroup.class})
    private String content;
}