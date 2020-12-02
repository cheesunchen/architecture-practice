package com.cheesun.config;

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

/**
 * @author cheesun
 * @date 2020/11/14
 */
@Configuration
@EnableSwagger2
public class Swagger2Config {

    // http://localhost:8088/swagger-ui.html
    // http://localhost:8088/doc.html

    /**
     * 配置核心配置 docket
     */
    @Bean
    public Docket createRestApi(){
        // 制定api类型为swagger2
        return new Docket(DocumentationType.SWAGGER_2)
                // 定义文档汇总信息
                .apiInfo(this.apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.cheesun.controller")) // 指定扫描的controller包
                .paths(PathSelectors.any())                                          // 所有controller
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("")
                .contact(new Contact("cheesun",
                        "",
                        "chenchuxiang@vip.qq.com"))
                .description("学习电商网站平台搭建")
                .version("0.0.1")
                .termsOfServiceUrl("http://47.94.12.240")
                .build();
    }
}
