package com.kang.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kang.VO.CommentLevelCountsVO;
import com.kang.VO.ItemCommentVO;
import com.kang.common.enums.CommentLevelEnum;
import com.kang.common.utils.DesensitizationUtil;
import com.kang.common.utils.PageUtils;
import com.kang.common.utils.Query;
import com.kang.mapper.ItemsCommentsMapper;
import com.kang.pojo.ItemsCommentsEntity;
import com.kang.service.ItemsCommentsService;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Map;


@Service("itemsCommentsService")
public class ItemsCommentsServiceImpl extends ServiceImpl<ItemsCommentsMapper, ItemsCommentsEntity> implements ItemsCommentsService {

    @Override
    public PageUtils queryItemComments(Map<String, Object> params) {
        IPage<ItemCommentVO> page = new Query<ItemCommentVO>().getPage(params);
        List<ItemCommentVO> itemCommentVOS = this.baseMapper.queryItemComments(page, params);
        if (!CollectionUtils.isEmpty(itemCommentVOS)) {
            itemCommentVOS.forEach(e -> e.setNickname(DesensitizationUtil.commonDisplay(e.getNickname())));
        }
        page.setRecords(itemCommentVOS);
        return new PageUtils(page);
    }

    @Override
    public CommentLevelCountsVO getCommentCntByItemId(String itemId) {
        List<ItemsCommentsEntity> commentsEntityList = this.baseMapper.getCommentCntByItemId(itemId);
        if (CollectionUtils.isEmpty(commentsEntityList)) {
            return null;
        }
        CommentLevelCountsVO res = new CommentLevelCountsVO();
        for (ItemsCommentsEntity comment : commentsEntityList) {
            if (CommentLevelEnum.GOOD.getCode().equals(comment.getCommentLevel())) {
                res.setGoodCounts(Integer.valueOf(comment.getId()));
            } else if (CommentLevelEnum.BAD.getCode().equals(comment.getCommentLevel())) {
                res.setBadCounts(Integer.valueOf(comment.getId()));
            } else if (CommentLevelEnum.NORMAL.getCode().equals(comment.getCommentLevel())) {
                res.setNormalCounts(Integer.valueOf(comment.getId()));
            }
        }
        res.setTotalCounts(res.getGoodCounts() + res.getNormalCounts() + res.getBadCounts());
        return res;
    }

}