package com.kang.common.config;

import io.swagger.annotations.ApiOperation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class Swagger2 {

//    http://localhost:8088/swagger-ui.html     原路径
//    http://localhost:8088/doc.html     原路径

    // 配置swagger2核心配置 docket
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)  // 指定api类型为swagger2
                    .apiInfo(apiInfo())                 // 用于定义api文档汇总信息
                    .select()
                    //加了ApiOperation注解的类，才生成接口文档
                    .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
//                    .apis(RequestHandlerSelectors.basePackage("com.kang.controller"))   // 指定controller包
                    .paths(PathSelectors.any())         // 所有controller
                    .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("ToBeBest API文档")        // 文档页标题
                .contact(new Contact("kang",
                        "https://goobad.cn",
                        "abc@imooc.com"))        // 联系人信息
                .description("架构师学习之路")  // 详细信息
                .version("1.0.1")   // 文档版本号
                .termsOfServiceUrl("https://goobad.cn") // 网站地址
                .build();
    }

}
