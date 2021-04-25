package com.kang.common.annotation;

import java.lang.annotation.*;

/**
 * 验证参数是否为空，是则返回 ErrorMsgEnum.ERROR_PARAM.getR()
 */
@Target({ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RequiredParam {
    boolean value() default true;
}
