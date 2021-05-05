package com.kang.service.impl.center;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kang.VO.center.CenterCommentVO;
import com.kang.common.utils.PageUtils;
import com.kang.common.utils.Query;
import com.kang.mapper.center.CenterCommentsMapper;
import com.kang.service.center.CenterCommentsService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("centerCommentsService")
public class CenterCommentsServiceImpl extends ServiceImpl<CenterCommentsMapper, CenterCommentVO> implements CenterCommentsService {

    @Override
    public PageUtils queryMyComments(Map<String, Object> map) {
        IPage<CenterCommentVO> page = new Query<CenterCommentVO>().getPage(map);
        List<CenterCommentVO> ordersVO = this.baseMapper.queryMyComments(page, map);
        return new PageUtils(page, ordersVO);
    }
}