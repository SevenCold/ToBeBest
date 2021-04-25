package com.kang.VO;

import lombok.Data;

/**
 * 子分类VO
 */
@Data
public class SubCategoryVO {

    private Integer subId;
    private String subName;
    private String subType;
    private Integer subFatherId;
}
