package com.kang.controller;

import com.kang.VO.CommentLevelCountsVO;
import com.kang.VO.ItemInfoVO;
import com.kang.common.annotation.RequestParamRequired;
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

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 商品信息相关controller
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
            @PathVariable String itemId) {
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
            @RequestParamRequired @RequestParam String itemId) {
        CommentLevelCountsVO res = itemsCommentsService.getCommentCntByItemId(itemId);
        return R.ok(res);
    }

    /**
     * 查询商品评论数量
     * @param itemId 商品id
     * @param level 评论等级
     * @param page 页码
     * @param pageSize 每页评论数量
     * @return 商品详情
     */
    @ApiOperation(value = "查询商品评论", notes = "根据商品id查询商品评论", httpMethod = "GET")
    @GetMapping("/comments")
    public R comments(
            @ApiParam(name = "itemId", value = "商品id", required = true)
            @RequestParamRequired @RequestParam String itemId,
            @ApiParam(name = "level", value = "评论等级")
            @RequestParam(required = false) Integer level,
            @ApiParam(name = "page", value = "页码")
            @RequestParam(required = false) String page,
            @ApiParam(name = "pageSize", value = "每页评论数量")
            @RequestParam(required = false) String pageSize) {
        Map<String, Object> queryMap = new HashMap<>();
        queryMap.put("itemId", itemId);
        queryMap.put("level", level);
        queryMap.put("page", page);
        queryMap.put("pageSize", pageSize);
        return R.ok(itemsCommentsService.queryItemComments(queryMap));
    }

    /**
     * 根据关键词查询商品
     * @param keywords 商品关键词
     * @param sort 排序类别
     * @param page 页码
     * @param pageSize 每页数量
     * @return 商品详情
     */
    @ApiOperation(value = "查询商品信息", notes = "根据关键词查询商品信息", httpMethod = "GET")
    @GetMapping("/search")
    public R search(
            @ApiParam(name = "keywords", value = "商品关键词", required = true)
            @RequestParamRequired @RequestParam String keywords,
            @ApiParam(name = "sort", value = "排序类别")
            @RequestParam(required = false) String sort,
            @ApiParam(name = "page", value = "页码")
            @RequestParam(required = false) String page,
            @ApiParam(name = "pageSize", value = "每页数量")
            @RequestParam(required = false) String pageSize) {
        Map<String, Object> queryMap = new HashMap<>();
        queryMap.put("keywords", keywords);
        queryMap.put("sort", sort);
        queryMap.put("page", page);
        queryMap.put("pageSize", pageSize);
        return R.ok(itemsService.searchItems(queryMap));
    }

    /**
     * 根据分类id查询商品
     * @param catId 分类id
     * @param sort 排序类别
     * @param page 页码
     * @param pageSize 每页数量
     * @return 商品详情
     */
    @ApiOperation(value = "查询分类商品信息", notes = "根据分类id查询商品信息", httpMethod = "GET")
    @GetMapping("/catItems")
    public R catItems(
            @ApiParam(name = "catId", value = "分类id", required = true)
            @RequestParamRequired @RequestParam String catId,
            @ApiParam(name = "sort", value = "排序类别")
            @RequestParam(required = false) String sort,
            @ApiParam(name = "page", value = "页码")
            @RequestParam(required = false) String page,
            @ApiParam(name = "pageSize", value = "每页数量")
            @RequestParam(required = false) String pageSize) {
        Map<String, Object> queryMap = new HashMap<>();
        queryMap.put("catId", catId);
        queryMap.put("sort", sort);
        queryMap.put("page", page);
        queryMap.put("pageSize", pageSize);
        return R.ok(itemsService.searchItemsByCat(queryMap));
    }

    /**
     * 根据规格ids查询商品
     * @param itemSpecIds 规格ids
     * @return 商品详情
     */
    @ApiOperation(value = "根据规格ids查询商品", notes = "根据规格ids查询商品", httpMethod = "GET")
    @GetMapping("/refresh")
    public R refresh(
            @ApiParam(name = "itemSpecIds", value = "规格ids", required = true, example = "1001,1002,1003")
            @RequestParamRequired @RequestParam String itemSpecIds) {
        return R.ok(itemsService.searchItemsBySpecIds(itemSpecIds));
    }

}
