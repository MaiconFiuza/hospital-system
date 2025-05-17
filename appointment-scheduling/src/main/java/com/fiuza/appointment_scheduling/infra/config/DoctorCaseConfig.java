package com.fiuza.appointment_scheduling.infra.config;

import com.fiuza.appointment_scheduling.core.gateway.DoctorGateway;
import com.fiuza.appointment_scheduling.core.usecases.doctor.CreateDoctorUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DoctorCaseConfig {


    @Bean
    public CreateDoctorUseCase doctorUseCase(DoctorGateway doctorGateway) {
        return new CreateDoctorUseCase(doctorGateway);
    }
}
