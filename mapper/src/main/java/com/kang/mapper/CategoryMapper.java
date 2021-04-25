package com.kang.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kang.VO.CategoryVO;
import com.kang.VO.NewItemsVO;
import com.kang.pojo.CategoryEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 商品分类 
 * 
 * @author kang
 * @email 784706982@qq.com
 * @date 2021-04-24 10:11:02
 */
@Mapper
public interface CategoryMapper extends BaseMapper<CategoryEntity> {

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
    List<NewItemsVO> getSixNewItemsLazy(Integer fatherId);
}
