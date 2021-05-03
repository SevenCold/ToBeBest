package com.kang.controller;

import com.kang.BO.ShopcartBO;
import com.kang.common.annotation.RequestParamRequired;
import com.kang.common.utils.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

/**
 * 购物车相关controller
 * @author kang
 * @email 784706982@qq.com
 * @date 2021-04-24 10:11:03
 */
@Api(value = "购物车相关", tags = "购物车相关接口")
@RestController
@RequestMapping("shopcart")
public class ShopController {

    /**
     * 加入购物车
     * @param userId 用户id
     * @param cartBO 购物车商品信息
     * @return 加入成功信息
     */
    @PostMapping("/add")
    @ApiOperation(value = "加入购物车", httpMethod = "POST", notes = "加入购物车")
    public R add(
            @ApiParam(name = "userId", value = "用户ID", required = true)
            @RequestParamRequired @RequestParam String userId,
            @RequestBody ShopcartBO cartBO) {
        System.out.println(cartBO);
        return R.ok();
    }

    /**
     * 删除购物车商品
     * @return 删除成功
     */
    @PostMapping("/del")
    @ApiOperation(value = "删除购物车商品", httpMethod = "POST", notes = "删除购物车商品")
    public R del(
            @ApiParam(name = "userId", value = "用户ID", required = true)
            @RequestParamRequired @RequestParam String userId,
            @ApiParam(name = "itemSpecId", value = "规格id", required = true)
            @RequestParamRequired @RequestParam String itemSpecId) {
        return R.ok();
    }
}
