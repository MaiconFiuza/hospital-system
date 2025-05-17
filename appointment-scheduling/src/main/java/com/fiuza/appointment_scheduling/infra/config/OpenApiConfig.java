package com.fiuza.appointment_scheduling.infra.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@OpenAPIDefinition
@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI appointmentScheduling() {
        return new OpenAPI()
                .info(
                        new Info().title("Appointment Scheduling")
                                .description("aplicação de agendamento de consulta")
                                .version("v0.0.1")
                                .license(new License().name("Apache 2.0")
                                        .url("https://github.com/MaiconFiuza/"))
                );
    }

}
