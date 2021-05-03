package com.kang.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kang.VO.SearchItemsVO;
import com.kang.VO.ShopcartVO;
import com.kang.common.exception.KangException;
import com.kang.common.utils.PageUtils;
import com.kang.common.utils.Query;
import com.kang.mapper.ItemsMapper;
import com.kang.pojo.ItemsEntity;
import com.kang.service.ItemsService;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Service("itemsService")
public class ItemsServiceImpl extends ServiceImpl<ItemsMapper, ItemsEntity> implements ItemsService {

    @Override
    public PageUtils searchItems(Map<String, Object> map) {
        IPage<SearchItemsVO> page = new Query<SearchItemsVO>().getPage(map);
        List<SearchItemsVO> itemsVOS = this.baseMapper.searchItems(page, map);
        return new PageUtils(page, itemsVOS);
    }

    @Override
    public PageUtils searchItemsByCat(Map<String, Object> map) {
        IPage<SearchItemsVO> page = new Query<SearchItemsVO>().getPage(map);
        List<SearchItemsVO> itemsVOS = this.baseMapper.searchItemsByCat(page, map);
        return new PageUtils(page, itemsVOS);
    }

    @Override
    public List<ShopcartVO> searchItemsBySpecIds(String specIds) {
        List<String> specIdList = Arrays.stream(
                specIds.split(","))
                .collect(Collectors.toList());
        return this.baseMapper.searchItemsBySpecIds(specIdList);
    }

    @Override
    public void decreaseStock(String specId, Integer count) {
        int res = this.baseMapper.decreaseStock(specId, count);
        if (res != 1) {
            throw new KangException("扣减库存失败！");
        }
    }
}