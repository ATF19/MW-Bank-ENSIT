package com.maibornwolff.ensit.bank.infrastructure.rest;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OneApiDocumentationConfiguration {

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .components(new Components())
                .info(buildApiInfo());
    }

    private Info buildApiInfo() {
        return new Info()
                .title("MW Bank ENSIT")
                .version("1.0.0")
                .description("MaibornWolff Bank - ENSIT");
    }

}
