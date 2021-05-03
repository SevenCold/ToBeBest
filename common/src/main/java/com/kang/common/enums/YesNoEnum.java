package com.kang.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum  YesNoEnum {


    YES(1, "是"),
    NO(0,"否")
    ;

    private final Integer code;
    private final String desc;
}
