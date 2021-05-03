package com.kang.common.interceptor;

import com.kang.common.annotation.RequestParamRequired;
import com.kang.common.enums.ErrorMsgEnum;
import com.kang.common.exception.KangException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.List;

/**
 * 验证参数是否为空， 与@RequestParam共同使用
 * 如果参数isEmpty() 抛出异常，异常信息为 ERROR_PARAM
 */
@Component
public class RequiredParamInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        List<String> list = getParamsName((HandlerMethod) handler);
        for (String s : list) {
            String parameter = request.getParameter(s);
            if (StringUtils.isEmpty(parameter)){
                throw new KangException(ErrorMsgEnum.ERROR_PARAM.getMsg(), ErrorMsgEnum.ERROR_PARAM.getStatus());
            }
        }
        return true;
    }

    /**
     * 获取使用了该注解的参数名称
     * @param handlerMethod 方法
     * @return 参数数组
     */
    private List<String> getParamsName(HandlerMethod handlerMethod) {
        Parameter[] parameters = handlerMethod.getMethod().getParameters();
        List<String> list = new ArrayList<>();
        for (Parameter parameter : parameters) {
            // 同时有@RequiredParam @RequestParam注解
            RequestParamRequired requiredParam = parameter.getAnnotation(RequestParamRequired.class);
            RequestParam requestParam = parameter.getAnnotation(RequestParam.class);
            if(requestParam != null && requiredParam != null && requiredParam.value()){
                list.add(parameter.getName());
            }
        }
        return list;
    }
}
