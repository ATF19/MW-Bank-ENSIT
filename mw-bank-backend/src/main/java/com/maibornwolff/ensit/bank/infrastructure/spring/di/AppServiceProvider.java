package com.maibornwolff.ensit.bank.infrastructure.spring.di;

import com.maibornwolff.ensit.bank.application.account.BankAccountAppService;
import com.maibornwolff.ensit.bank.persitence.account.BankAccountRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppServiceProvider {

    @Bean
    public BankAccountAppService bankAccountAppService() {
        return new BankAccountAppService(new BankAccountRepository());
    }
}
