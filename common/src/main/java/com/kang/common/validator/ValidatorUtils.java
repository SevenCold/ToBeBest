package com.kang.common.validator;

import com.kang.common.enums.ErrorMsgEnum;
import com.kang.common.exception.KangException;
import org.springframework.util.CollectionUtils;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.List;
import java.util.Set;

/**
 * hibernate-validator校验工具类
 *
 * 参考文档：http://docs.jboss.org/hibernate/validator/5.4/reference/en-US/html_single/
 *
 * @author Mark sunlightcs@gmail.com
 */
public class ValidatorUtils {
    private static final Validator validator;

    static {
        validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    /**
     * 校验对象
     * @param object        待校验对象
     * @param groups        待校验的组
     * @throws KangException  校验不通过，则报KangException异常
     */
    public static void validateEntity(Object object, Class<?>... groups)
            throws KangException {
        Set<ConstraintViolation<Object>> constraintViolations = validator.validate(object, groups);
        if (!constraintViolations.isEmpty()) {
            // 返回全部不符合提示
//            StringBuilder msg = new StringBuilder();
//            for(ConstraintViolation<Object> constraint:  constraintViolations){
//                msg.append(constraint.getMessage()).append("<br>");
//            }
//            throw new KangException(msg.toString());
            // 只返回头一个不符合提示
            ConstraintViolation<Object> first = constraintViolations.iterator().next();
            throw new KangException(first.getMessage());
        }
    }

    /**
     * 校验对象数组
     * @param list        待校验对象数组
     * @param groups        待校验的组
     * @throws KangException  如果数组为空，报ERROR_PARAM异常
     *                        如果有一次校验失败，报KangException异常
     */
    public static void validateList(List<?> list, Class<?>... groups)
            throws KangException {
        if (CollectionUtils.isEmpty(list)) {
            throw new KangException(ErrorMsgEnum.ERROR_PARAM.getMsg());
        }
        for (Object object : list) {
            Set<ConstraintViolation<Object>> constraintViolations = validator.validate(object, groups);
            if (!constraintViolations.isEmpty()) {
                ConstraintViolation<Object> first = constraintViolations.iterator().next();
                throw new KangException(first.getMessage());
            }
        }
    }
}
