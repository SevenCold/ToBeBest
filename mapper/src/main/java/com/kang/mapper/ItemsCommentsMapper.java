package com.kang.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kang.VO.ItemCommentVO;
import com.kang.pojo.ItemsCommentsEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

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

    /**
     * 根据商品id查询商品评论
     * @param page 分页数据
     * @param map map.itemId 商品id
     *            map.level 商品评论等级 （可能为空）
     * @return 商品评论信息
     */
    List<ItemCommentVO> queryItemComments(IPage<ItemCommentVO> page, @Param("paramsMap") Map<String, Object> map);
}
