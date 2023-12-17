package com.rfltech.cardapiodigital.client.messages.requests;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class DeliveryMuchAuthRequest {

    @JsonProperty("client_id")
    private String clientId;

    @JsonProperty("client_secret")
    private String clientSecret;

    @JsonProperty("username")
    private String userName;

    private String password;

    @JsonProperty("grant_type")
    private String grantType;
}