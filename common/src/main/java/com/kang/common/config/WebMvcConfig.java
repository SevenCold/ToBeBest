/**
 * Copyright (c) 2016-2019 人人开源 All rights reserved.
 *
 * https://www.renren.io
 *
 * 版权所有，侵权必究！
 */

package com.kang.common.config;

import com.kang.common.interceptor.RequiredParamInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * MVC配置
 *
 * @author Mark sunlightcs@gmail.com
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Autowired
    private RequiredParamInterceptor requiredParamInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 验证参数是否为空
        registry.addInterceptor(requiredParamInterceptor).addPathPatterns("/**");
    }
}