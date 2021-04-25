package com.kang.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kang.VO.CategoryVO;
import com.kang.VO.NewItemsVO;
import com.kang.mapper.CategoryMapper;
import com.kang.pojo.CategoryEntity;
import com.kang.service.CategoryService;
import org.springframework.stereotype.Service;

import java.util.List;


@Service("categoryService")
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, CategoryEntity> implements CategoryService {

    @Override
    public List<CategoryVO> getSubCatByFatherId(Integer fatherId) {
        return this.baseMapper.getSubCatByFatherId(fatherId);
    }

    @Override
    public List<NewItemsVO> getSixNewItemsLazy(Integer fatherId) {
        return this.baseMapper.getSixNewItemsLazy(fatherId);
    }
}