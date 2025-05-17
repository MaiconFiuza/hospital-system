package com.fiuza.appointment_scheduling.infra.config;

import com.fiuza.appointment_scheduling.core.gateway.PatientGateway;
import com.fiuza.appointment_scheduling.core.usecases.patient.CreatePatientUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PatientCaseConfig {

    @Bean
    public CreatePatientUseCase createPatientUseCase(PatientGateway patientGateway) {
        return new CreatePatientUseCase(patientGateway);
    }
}
