package com.rfltech.cardapiodigital.config.properties;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "delivery-much")
public class DeliveryMuchProperties {

    private Autenticacao autenticacao;

    @Data
    public static class Autenticacao {

        private String host;

        @JsonProperty("client_id")
        private String clientId;

        @JsonProperty("client_secret")
        private String clientSecret;

        @JsonProperty("username")
        private String userName;

        @JsonProperty("password")
        private String passWord;

        @JsonProperty("grant_type")
        private String grantType;
    }
}