package com.fiuza.appointment_scheduling.infra.config;

import com.fiuza.appointment_scheduling.core.gateway.*;
import com.fiuza.appointment_scheduling.core.usecases.consultation.CreateConsultationUseCase;
import com.fiuza.appointment_scheduling.core.usecases.consultation.DeleteConsultationUseCase;
import com.fiuza.appointment_scheduling.core.usecases.consultation.GetConsultationUseCase;
import com.fiuza.appointment_scheduling.core.usecases.consultation.UpdateConsultationUseCase;
import com.fiuza.appointment_scheduling.infra.adapter.ConsultationRepositoryImp;
import com.fiuza.appointment_scheduling.infra.adapter.DoctorRepositoryImp;
import com.fiuza.appointment_scheduling.infra.adapter.NurseRepositoryImp;
import com.fiuza.appointment_scheduling.infra.adapter.PatientRepositoryImp;
import com.fiuza.appointment_scheduling.infra.repository.ConsultationRepository;
import com.fiuza.appointment_scheduling.infra.repository.DoctorRepository;
import com.fiuza.appointment_scheduling.infra.repository.NurseRepository;
import com.fiuza.appointment_scheduling.infra.repository.PatientRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConsultationCaseConfig {

    @Bean
    public ConsultationGateway consultationGateway(ConsultationRepository consultationRepository) {
        return new ConsultationRepositoryImp(consultationRepository);
    }

    @Bean
    public NurseGateway nurseGateway(NurseRepository nurseRepository) {
        return new NurseRepositoryImp(nurseRepository);
    }

    @Bean
    public PatientGateway patientGateway(PatientRepository patientRepository) {
        return new PatientRepositoryImp(patientRepository);
    }

    @Bean
    public DoctorGateway doctorGateway(DoctorRepository doctorRepository) {
        return new DoctorRepositoryImp(doctorRepository);
    }

    @Bean
    public CreateConsultationUseCase createConsultationUseCase(
            ConsultationGateway consultationGateway,
            PatientGateway patientGateway,
            NurseGateway nurseGateway,
            DoctorGateway doctorGateway,
            AppointmentEventGateway appointmentEventGateway
    ) {
        return new CreateConsultationUseCase(
                consultationGateway,
                patientGateway,
                nurseGateway,
                doctorGateway,
                appointmentEventGateway
        );
    }

    @Bean
    public UpdateConsultationUseCase updateConsultationUseCase(
            ConsultationGateway consultationGateway,
            AppointmentEventGateway appointmentEventGateway
    ) {
        return new UpdateConsultationUseCase(
                consultationGateway,
                appointmentEventGateway
        );
    }

    @Bean
    public DeleteConsultationUseCase deleteConsultationUseCase(
            ConsultationGateway consultationGateway,
            AppointmentEventGateway appointmentEventGateway
    ) {
        return new DeleteConsultationUseCase(
                consultationGateway,
                appointmentEventGateway
        );
    }

    @Bean
    public GetConsultationUseCase getConsultationUseCase(ConsultationGateway consultationGateway) {
        return new GetConsultationUseCase(consultationGateway);
    }
}
