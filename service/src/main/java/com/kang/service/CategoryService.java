package com.kang.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.kang.VO.CategoryVO;
import com.kang.VO.NewItemsVO;
import com.kang.pojo.CategoryEntity;

import java.util.List;

/**
 * 商品分类 
 *
 * @author kang
 * @email 784706982@qq.com
 * @date 2021-04-24 10:11:02
 */
public interface CategoryService extends IService<CategoryEntity> {

    /**
     * 根据一级分类id查询子分类信息
     * @param fatherId 一级分类id
     * @return 子分类信息
     */
    List<CategoryVO> getSubCatByFatherId(Integer fatherId);

    /**
     * 查询首页一级分类下的6条最新商品数据
     * @param fatherId 一级分类id
     * @return 商品数据
     */
    public List<NewItemsVO> getSixNewItemsLazy(Integer fatherId);
}

