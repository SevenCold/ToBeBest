package com.kang.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/** 文件上传的文件类型
 * @author 汪少
 * @date 2021/4/7 16:06
 */
@Getter
@AllArgsConstructor
public enum FileTypeEnum {

    JPEG("jpeg", "JPEG"),
    JPG("jpg", "jpg"),
    PNG("png", "png");

    /**
     * 类型
     */
    private final String type;

    /**
     * 详细信息
     */
    private final String desc;
}
