package com.rfltech.cardapiodigital;

import com.rfltech.cardapiodigital.client.DeliveryMuchClient;
import com.rfltech.cardapiodigital.client.messages.requests.DeliveryMuchAuthRequest;
import com.rfltech.cardapiodigital.client.messages.response.DeliveryMuchAuthResponse;
import com.rfltech.cardapiodigital.config.properties.DeliveryMuchProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableFeignClients
@EnableScheduling
@SpringBootApplication
public class CardapioDigitalApplication {

    @Autowired
    private DeliveryMuchClient deliveryMuchClient;

    @Autowired
    private DeliveryMuchProperties deliveryMuchProperties;

    public static void main(String[] args) {
        SpringApplication.run(CardapioDigitalApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner() {
        DeliveryMuchAuthRequest request = DeliveryMuchAuthRequest.builder()
                .clientSecret(deliveryMuchProperties.getAutenticacao().getClientSecret())
                .clientId(deliveryMuchProperties.getAutenticacao().getClientId())
                .userName(deliveryMuchProperties.getAutenticacao().getUserName())
                .password(deliveryMuchProperties.getAutenticacao().getPassWord())
                .grantType(deliveryMuchProperties.getAutenticacao().getGrantType()).build();

        DeliveryMuchAuthResponse autenticar = deliveryMuchClient.autenticar(request);
        return args -> System.out.println(autenticar);
    }
}