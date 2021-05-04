package com.kang.service.impl.center;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kang.VO.center.CenterOrdersVO;
import com.kang.common.utils.PageUtils;
import com.kang.common.utils.Query;
import com.kang.mapper.center.CenterOrdersMapper;
import com.kang.service.center.CenterOrdersService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service("centerOrdersService")
public class CenterOrdersServiceImpl extends ServiceImpl<CenterOrdersMapper, CenterOrdersVO> implements CenterOrdersService {

    @Override
    public PageUtils queryMyOrders(Map<String, Object> map) {
        IPage<CenterOrdersVO> page = new Query<CenterOrdersVO>().getPage(map);
        List<CenterOrdersVO> ordersVO = this.baseMapper.queryMyOrders(page, map);
        return new PageUtils(page, ordersVO);
    }
}