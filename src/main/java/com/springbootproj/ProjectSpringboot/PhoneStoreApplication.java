package com.springbootproj.ProjectSpringboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class PhoneStoreApplication {
    public static void main(String[] args) {
        SpringApplication.run(PhoneStoreApplication.class, args);
    }
    
    @Bean  // ← add this method
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
