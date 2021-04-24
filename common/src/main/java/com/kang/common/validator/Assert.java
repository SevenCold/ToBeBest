package com.kang.common.validator;

import com.kang.common.exception.KangException;
import org.apache.commons.lang.StringUtils;

/**
 * 数据校验
 *
 * @author kang
 */
public abstract class Assert {

    public static void isBlank(String str, String message) {
        if (StringUtils.isBlank(str)) {
            throw new KangException(message);
        }
    }

    public static void isNull(Object object, String message) {
        if (object == null) {
            throw new KangException(message);
        }
    }
}
