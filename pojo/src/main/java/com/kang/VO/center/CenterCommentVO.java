package com.kang.VO.center;

import lombok.Data;

import java.util.Date;

/**
 * 个人中心-我的评价
 */
@Data
public class CenterCommentVO {

    private String commentId;
    private String content;
    private Date createdTime;
    private String itemId;
    private String itemName;
    private String specName;
    private String itemImg;
}