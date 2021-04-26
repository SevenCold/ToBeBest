package com.kang.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.kang.VO.ShopcartVO;
import com.kang.common.utils.PageUtils;
import com.kang.pojo.ItemsEntity;
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
public interface ItemsService extends IService<ItemsEntity> {

    /**
     * 根据关键字查询商品
     * @param map 查询条件
     * @return 商品信息
     */
    PageUtils searchItems(@Param("paramsMap") Map<String, Object> map);

    /**
     * 根据分类id查询商品
     * @param map 查询条件
     * @return 商品信息
     */
    PageUtils searchItemsByCat(@Param("paramsMap") Map<String, Object> map);

    /**
     * 根据规格id查询商品
     * @param specIds 多个规格id拼接字符串（,)
     * @return 商品信息
     */
    List<ShopcartVO> searchItemsBySpecIds(String specIds);
}

