package com.cheesun.config;

import org.apache.tomcat.util.http.LegacyCookieProcessor;
import org.springframework.boot.web.embedded.tomcat.TomcatContextCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @auther cheesun
 * @date 2020/12/16
 * @time 9:33
 * @description
 */
@Configuration
public class TomcatConfiguration {
    @Bean
    public TomcatContextCustomizer sameSiteCookiesConfig() {
        return context -> {
            final LegacyCookieProcessor cookieProcessor = new LegacyCookieProcessor();
            context.setCookieProcessor(cookieProcessor);
        };
    }
}
