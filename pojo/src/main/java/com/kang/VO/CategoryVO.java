package com.kang.VO;

import lombok.Data;

import java.util.List;

/**
 * 分类vo
 */
@Data
public class CategoryVO {


    private Integer id;
    private String name;
    private String type;
    private Integer fatherId;

    private List<SubCategoryVO> subCatList;

}
