package com.rfltech.cardapiodigital;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableFeignClients
@EnableScheduling
@SpringBootApplication
public class CardapioDigitalApplication {

    public static void main(String[] args) {
        SpringApplication.run(CardapioDigitalApplication.class, args);
    }
}