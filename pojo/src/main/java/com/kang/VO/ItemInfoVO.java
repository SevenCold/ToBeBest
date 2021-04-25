package com.kang.VO;

import com.kang.pojo.ItemsEntity;
import com.kang.pojo.ItemsImgEntity;
import com.kang.pojo.ItemsParamEntity;
import com.kang.pojo.ItemsSpecEntity;
import lombok.Data;

import java.util.List;

/**
 * 商品详情VO
 */
@Data
public class ItemInfoVO {

    private ItemsEntity item;
    private List<ItemsImgEntity> itemImgList;
    private List<ItemsSpecEntity> itemSpecList;
    private ItemsParamEntity itemParams;
}
