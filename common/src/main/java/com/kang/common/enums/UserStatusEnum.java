package com.kang.common.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum UserStatusEnum {

    NORMAL(0, "正常"),
    NOT_ENABLED(1, "未启用"),
    DISABLE(2, "禁用")
    ;

    private int value;

    private String desc;

}
