package com.kang.common.annotation;

import com.kang.common.validator.MobileValidation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * @author 汪少
 * @date 2021/4/7 14:24
 */
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = {MobileValidation.class})
public @interface Mobile {

    String message() default "手机号格式错误";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
