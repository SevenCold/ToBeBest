package com.kang.service.center;

import com.baomidou.mybatisplus.extension.service.IService;
import com.kang.VO.center.CenterCommentVO;
import com.kang.common.utils.PageUtils;

import java.util.Map;

/**
 * 个人中心--我的评价
 * @author kang
 * @email 784706982@qq.com
 * @date 2021-04-24 10:11:00
 */
public interface CenterCommentsService extends IService<CenterCommentVO> {

    /**
     * 根据用户id 查询我的评价信息
     * @param map 查询条件
     * @return 分页的 我的评价信息
     */
    PageUtils queryMyComments(Map<String, Object> map);
}

