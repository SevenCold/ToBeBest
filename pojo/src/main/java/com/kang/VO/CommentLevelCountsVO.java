package com.kang.VO;

import lombok.Data;

/**
 * 用于展示商品评价数量的vo
 */
@Data
public class CommentLevelCountsVO {

    public Integer totalCounts = 0;
    public Integer goodCounts = 0;
    public Integer normalCounts = 0;
    public Integer badCounts = 0;
}
