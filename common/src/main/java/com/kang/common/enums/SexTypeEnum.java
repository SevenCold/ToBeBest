package com.kang.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum SexTypeEnum {

    WOMAN(0, "女"),
    MAN(1,"男"),
    SECRET(2, "保密")
    ;

    private final Integer code;
    private final String desc;
}
