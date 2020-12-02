package com.cheesun.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * @author cheesun
 * @date 2020/11/15
 */
@Configuration
public class CorsConfig {
    public CorsConfig() {

    }

    @Bean
    public CorsFilter corsFilter() {
        /**
         * 1 添加配置信息
         */
        CorsConfiguration config = new CorsConfiguration();
        // 允许的来源地址
        config.addAllowedOrigin("http://localhost:8080");

        // 设置是否发送cookie信息
        config.setAllowCredentials(true);

        // 设置允许的请求方式
        config.addAllowedMethod("*");

        // 设置允许的请求头
        config.addAllowedHeader("*");

        /**
         * 2 为url添加映射路径
         */
        UrlBasedCorsConfigurationSource corsSource = new UrlBasedCorsConfigurationSource();
        corsSource.registerCorsConfiguration("/**", config);

        /**
         * 3 返回定义好的source
         */
        return new CorsFilter(corsSource);
    }
}
