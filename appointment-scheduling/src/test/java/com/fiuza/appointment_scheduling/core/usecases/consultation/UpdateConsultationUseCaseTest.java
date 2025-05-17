package com.fiuza.appointment_scheduling.core.usecases.consultation;

import com.fiuza.appointment_scheduling.core.dto.request.ConsultationDTO;
import com.fiuza.appointment_scheduling.core.dto.request.UpdateConsultationDTO;
import com.fiuza.appointment_scheduling.core.dto.response.Appointment;
import com.fiuza.appointment_scheduling.core.entities.consulation.Consultation;
import com.fiuza.appointment_scheduling.core.entities.doctor.Doctor;
import com.fiuza.appointment_scheduling.core.entities.nurse.Nurse;
import com.fiuza.appointment_scheduling.core.entities.patient.Patient;
import com.fiuza.appointment_scheduling.core.gateway.*;
import com.fiuza.appointment_scheduling.helper.dto.AppointmentHelper;
import com.fiuza.appointment_scheduling.helper.dto.ConsultationDtoHelper;
import com.fiuza.appointment_scheduling.helper.dto.UpdateConsultationDtoHelper;
import com.fiuza.appointment_scheduling.helper.entities.consultation.ConsultationHelper;
import com.fiuza.appointment_scheduling.helper.entities.doctor.DoctorHelper;
import com.fiuza.appointment_scheduling.helper.entities.nurse.NurseHelper;
import com.fiuza.appointment_scheduling.helper.entities.patient.PatientHelper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
public class UpdateConsultationUseCaseTest {
    @Mock
    ConsultationGateway consultationGateway;

    @Mock
    AppointmentEventGateway appointmentEventGateway;

    @InjectMocks
    UpdateConsultationUseCase updateConsultationUseCase;

    AutoCloseable mock;

    @BeforeEach
    void setup() {
        mock = MockitoAnnotations.openMocks(this);
        updateConsultationUseCase = new UpdateConsultationUseCase(
                consultationGateway,
                appointmentEventGateway
        );
    }

    @AfterEach
    void teardown() throws Exception{
        mock.close();
    }

    @Test
    void update_consultation_with_success() {
        //arrange
        UpdateConsultationDTO consultationDTO = UpdateConsultationDtoHelper.defaultDTO();
        Appointment appointment = AppointmentHelper.defaultUpdatedDTO();
        Consultation consultation = ConsultationHelper.createConsultationWithId();

        when(consultationGateway.findConsultationById(1L)).thenReturn(Optional.of(consultation));
        doNothing().when(appointmentEventGateway).publishAppointmentUpdated(appointment);
        when(consultationGateway.save(any(Consultation.class))).thenReturn(consultation);

        //act
        var updatedConsultation = updateConsultationUseCase.execute(consultationDTO, 1L);

        //assert
        ArgumentCaptor<Consultation> consultationCaptor = ArgumentCaptor.forClass(Consultation.class);
        verify(consultationGateway, times(1)).save(consultationCaptor.capture());

        Consultation capturedConsultation = consultationCaptor.getValue();

        assertThat(capturedConsultation.getDoctor().getCrm()).isEqualTo(consultation.getDoctor().getCrm());
        assertThat(capturedConsultation.getPatient().getName()).isEqualTo(consultation.getPatient().getName());
        assertThat(updatedConsultation).isNotNull();

    }
}
