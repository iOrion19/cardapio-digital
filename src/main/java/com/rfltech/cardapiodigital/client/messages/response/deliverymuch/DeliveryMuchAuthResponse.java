package com.rfltech.cardapiodigital.client.messages.response.deliverymuch;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeliveryMuchAuthResponse {

    @JsonProperty("access_token")
    private String tokenAcesso;

    private String scope;

    @JsonProperty("expires_in")
    private String expiracao;
}