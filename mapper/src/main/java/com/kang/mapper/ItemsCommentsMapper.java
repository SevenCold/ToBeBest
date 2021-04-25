package com.kang.mapper;

import com.kang.pojo.ItemsCommentsEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 商品评价表 
 * 
 * @author kang
 * @email 784706982@qq.com
 * @date 2021-04-24 10:11:00
 */
@Mapper
public interface ItemsCommentsMapper extends BaseMapper<ItemsCommentsEntity> {

    /**
     * 通过商品id分组查询评价个数
     * @param itemId 商品id
     * @return 好评、中评、差评的个数
     */
    List<ItemsCommentsEntity> getCommentCntByItemId(String itemId);
}
