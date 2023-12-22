package com.rfltech.cardapiodigital.config;

import com.rfltech.cardapiodigital.config.properties.CardapioDigitalProperties;
import feign.Logger;
import feign.RequestInterceptor;
import org.springframework.context.annotation.Bean;

public class CardapioDigitalFeignConfig {

    private final CardapioDigitalProperties cardapioDigitalProperties;

    private static final String AUTHORIZATION_HEADER = "Bearer";

    public CardapioDigitalFeignConfig(CardapioDigitalProperties cardapioDigitalProperties) {
        this.cardapioDigitalProperties = cardapioDigitalProperties;
    }

    @Bean
    Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }

    @Bean
    public RequestInterceptor requestInterceptor() {
        return requestTemplate -> {
            requestTemplate.header("Content-Type", "application/json");
            requestTemplate.header(AUTHORIZATION_HEADER, cardapioDigitalProperties.getToken());
        };
    }
}
