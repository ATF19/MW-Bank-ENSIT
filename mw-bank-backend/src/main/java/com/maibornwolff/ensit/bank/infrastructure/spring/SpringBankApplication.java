package com.maibornwolff.ensit.bank.infrastructure.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.maibornwolff.ensit.bank"})
public class SpringBankApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBankApplication.class, args);
    }
}
