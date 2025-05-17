package com.fiuza.appointment_scheduling.infra.config;


import com.fiuza.appointment_scheduling.core.gateway.NurseGateway;
import com.fiuza.appointment_scheduling.core.usecases.nurse.CreateNurseUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class NurseCaseConfig {

    @Bean
    public CreateNurseUseCase createNurseUseCase(NurseGateway nurseGateway) {
        return new CreateNurseUseCase(nurseGateway);
    }
}
