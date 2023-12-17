package com.rfltech.cardapiodigital;

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
    private DeliveryMuchProperties deliveryMuchProperties;

    public static void main(String[] args) {
        SpringApplication.run(CardapioDigitalApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner() {
        return args -> System.out.println(deliveryMuchProperties.getAutenticacao().getGrantType());
    }
}