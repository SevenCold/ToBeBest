package com.kang.controller;

import com.kang.VO.CommentLevelCountsVO;
import com.kang.VO.ItemInfoVO;
import com.kang.common.annotation.RequiredParam;
import com.kang.common.utils.R;
import com.kang.pojo.ItemsEntity;
import com.kang.pojo.ItemsImgEntity;
import com.kang.pojo.ItemsParamEntity;
import com.kang.pojo.ItemsSpecEntity;
import com.kang.service.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * 商品表 商品信息相关表：分类表，商品图片表，商品规格表，商品参数表
 *
 * @author kang
 * @email 784706982@qq.com
 * @date 2021-04-24 10:11:01
 */
@Api(value = "商品信息", tags = "商品信息相关接口")
@RestController
@RequestMapping("items")
public class ItemsController {
    @Autowired
    private ItemsService itemsService;

    @Autowired
    private ItemsImgService itemsImgService;

    @Autowired
    private ItemsSpecService itemsSpecService;

    @Autowired
    private ItemsParamService itemsParamService;

    @Autowired
    private ItemsCommentsService itemsCommentsService;


    /**
     * 查询商品详情
     * @param itemId 商品id
     * @return 商品详情
     */
    @ApiOperation(value = "查询商品详情", notes = "根据商品id查询商品详情", httpMethod = "GET")
    @GetMapping("/info/{itemId}")
    public R sixNewItems(
            @ApiParam(name = "itemId", value = "商品id", required = true)
            @RequiredParam @PathVariable String itemId) {
        ItemInfoVO itemInfoVO = new ItemInfoVO();
        ItemsEntity itemsEntity = itemsService.lambdaQuery().eq(ItemsEntity::getId, itemId).one();
        itemInfoVO.setItem(itemsEntity);
        List<ItemsImgEntity> imgEntityList = itemsImgService.lambdaQuery().eq(ItemsImgEntity::getItemId, itemId).list();
        itemInfoVO.setItemImgList(imgEntityList);
        List<ItemsSpecEntity> specEntityList = itemsSpecService.lambdaQuery().eq(ItemsSpecEntity::getItemId, itemId).list();
        itemInfoVO.setItemSpecList(specEntityList);
        ItemsParamEntity paramEntity = itemsParamService.lambdaQuery().eq(ItemsParamEntity::getItemId, itemId).one();
        itemInfoVO.setItemParams(paramEntity);
        return R.ok(itemInfoVO);
    }

    /**
     * 查询商品评论数量
     * @param itemId 商品id
     * @return 商品详情
     */
    @ApiOperation(value = "查询商品评论数量", notes = "根据商品id查询商品评论数量", httpMethod = "GET")
    @GetMapping("/commentLevel")
    public R commentLevel(
            @ApiParam(name = "itemId", value = "商品id", required = true)
            @RequiredParam @RequestParam String itemId) {
        CommentLevelCountsVO res = itemsCommentsService.getCommentCntByItemId(itemId);
        return R.ok(res);
    }

}
