package com.fiuza.appointment_scheduling.core.usecases.consultation;

import com.fiuza.appointment_scheduling.core.dto.response.Appointment;
import com.fiuza.appointment_scheduling.core.entities.consulation.Consultation;
import com.fiuza.appointment_scheduling.core.gateway.AppointmentEventGateway;
import com.fiuza.appointment_scheduling.core.gateway.ConsultationGateway;
import com.fiuza.appointment_scheduling.helper.dto.AppointmentHelper;
import com.fiuza.appointment_scheduling.helper.entities.consultation.ConsultationHelper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
public class DeleteConsultationUseCaseTest {
    @Mock
    ConsultationGateway consultationGateway;

    @Mock
    AppointmentEventGateway appointmentEventGateway;

    @InjectMocks
    DeleteConsultationUseCase deleteConsultationUseCase;

    AutoCloseable mock;

    @BeforeEach
    void setup() {
        mock = MockitoAnnotations.openMocks(this);
        deleteConsultationUseCase = new DeleteConsultationUseCase(
                consultationGateway,
                appointmentEventGateway
        );
    }

    @AfterEach
    void teardown() throws Exception{
        mock.close();
    }

    @Test
    void delete_consultation_with_success() {
        //arrange
        Appointment appointment = AppointmentHelper.defaultDeleteDTO();
        Consultation consultation = ConsultationHelper.createConsultationWithId();

        when(consultationGateway.findConsultationById(1L)).thenReturn(Optional.of(consultation));
        doNothing().when(consultationGateway).delete(any(Long.class));
        doNothing().when(appointmentEventGateway).publishAppointmentCanceled(appointment);

        //act
        deleteConsultationUseCase.execute(1L);

        //assert
        verify(consultationGateway, times(1)).delete(any(Long.class));


    }
}
