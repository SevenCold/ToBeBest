package com.kang.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.kang.VO.SearchItemsVO;
import com.kang.VO.ShopcartVO;
import com.kang.pojo.ItemsEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 商品表 商品信息相关表：分类表，商品图片表，商品规格表，商品参数表
 * 
 * @author kang
 * @email 784706982@qq.com
 * @date 2021-04-24 10:11:01
 */
@Mapper
public interface ItemsMapper extends BaseMapper<ItemsEntity> {

    /**
     * 根据关键字查询商品
     * @param page 分页数据
     * @param map 查询条件
     * @return 商品信息
     */
    List<SearchItemsVO> searchItems(IPage<SearchItemsVO> page, @Param("paramsMap") Map<String, Object> map);

    /**
     * 根据分类id查询商品
     * @param page 分页数据
     * @param map 查询条件
     * @return 商品信息
     */
    List<SearchItemsVO> searchItemsByCat(IPage<SearchItemsVO> page, @Param("paramsMap") Map<String, Object> map);

    /**
     * 根据规格id查询商品
     * @param paramsList 规格id数组
     * @return 商品信息
     */
    List<ShopcartVO> searchItemsBySpecIds(@Param("paramsList") List<String> paramsList);

    /**
     * 通过规格id扣减库存
     * @param specId 规格id
     * @param count 购买数量
     */
    int decreaseStock(@Param("specId") String specId, @Param("count") Integer count);
}
