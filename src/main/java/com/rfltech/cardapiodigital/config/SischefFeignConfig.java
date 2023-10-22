package com.rfltech.cardapiodigital.config;

import com.rfltech.cardapiodigital.config.properties.CardapioDigitalProperties;
import com.rfltech.cardapiodigital.config.properties.SischefProperties;
import feign.Logger;
import feign.RequestInterceptor;
import org.springframework.context.annotation.Bean;

public class SischefFeignConfig {

    private final SischefProperties sischefProperties;

    private static final String AUTHORIZATION_HEADER = "token-integracao";

    public SischefFeignConfig(SischefProperties sischefProperties) {
        this.sischefProperties = sischefProperties;
    }

    @Bean
    Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }

    @Bean
    public RequestInterceptor requestInterceptor() {
        return requestTemplate -> {
            requestTemplate.header("Content-Type", "application/json");
            requestTemplate.header(AUTHORIZATION_HEADER, sischefProperties.getToken());
        };

    }
}
