package com.kang.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.kang.VO.CommentLevelCountsVO;
import com.kang.common.utils.PageUtils;
import com.kang.pojo.ItemsCommentsEntity;

import java.util.Map;

/**
 * 商品评价表 
 *
 * @author kang
 * @email 784706982@qq.com
 * @date 2021-04-24 10:11:00
 */
public interface ItemsCommentsService extends IService<ItemsCommentsEntity> {

    /**
     * 根据商品id分页查询商品评论
     * @param params
     * @return 商品评论信息
     */
    PageUtils queryItemComments(Map<String, Object> params);

    /**
     * 通过商品id分组查询评价个数
     * @param itemId 商品id
     * @return 评价个数信息
     */
    CommentLevelCountsVO getCommentCntByItemId(String itemId);
}

