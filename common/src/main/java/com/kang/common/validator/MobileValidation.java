package com.kang.common.validator;

import com.kang.common.annotation.Mobile;
import org.apache.commons.lang.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

/** 验证是否为手机号
 * @author 汪少
 * @date 2021/4/7 14:26
 */
public class MobileValidation implements ConstraintValidator<Mobile, String> {

    private static final Pattern PATTERN = Pattern.compile( "^1[345678]\\d{9}$");
    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if (StringUtils.isEmpty(s)) {
            return false;
        } else {
            return PATTERN.matcher(s).matches();
        }
    }
}
