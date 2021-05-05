package com.kang.mapper.center;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.kang.VO.center.CenterCommentVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 个人中心-我的评价
 * @author kang
 * @email 784706982@qq.com
 * @date 2021-04-24 10:11:00
 */
@Mapper
public interface CenterCommentsMapper extends BaseMapper<CenterCommentVO> {

    /**
     * 根据用户id 查询我的评价信息
     * @param page 分页数据
     * @param map 查询条件
     * @return 我的评价信息
     */
    List<CenterCommentVO> queryMyComments(IPage<CenterCommentVO> page, @Param("paramsMap") Map<String, Object> map);
}
