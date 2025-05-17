package com.fiuza.appointment_scheduling.core.usecases.consultation;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.fiuza.appointment_scheduling.core.dto.request.ConsultationDTO;
import com.fiuza.appointment_scheduling.core.dto.response.Appointment;
import com.fiuza.appointment_scheduling.core.entities.consulation.Consultation;
import com.fiuza.appointment_scheduling.core.entities.doctor.Doctor;
import com.fiuza.appointment_scheduling.core.entities.nurse.Nurse;
import com.fiuza.appointment_scheduling.core.entities.patient.Patient;
import com.fiuza.appointment_scheduling.core.gateway.*;
import com.fiuza.appointment_scheduling.helper.dto.AppointmentHelper;
import com.fiuza.appointment_scheduling.helper.dto.ConsultationDtoHelper;
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

@ExtendWith(MockitoExtension.class)
public class CreateConsultationUseCaseTest {

    @Mock
    ConsultationGateway consultationGateway;

    @Mock
    PatientGateway patientGateway;

    @Mock
    NurseGateway nurseGateway;

    @Mock
    DoctorGateway doctorGateway;

    @Mock
    AppointmentEventGateway appointmentEventGateway;

    @InjectMocks
    CreateConsultationUseCase createConsultationUseCase;

    AutoCloseable mock;

    @BeforeEach
    void setup() {
        mock = MockitoAnnotations.openMocks(this);
        createConsultationUseCase = new CreateConsultationUseCase(
                consultationGateway,
                patientGateway,
                nurseGateway,
                doctorGateway,
                appointmentEventGateway
        );
    }

    @AfterEach
    void teardown() throws Exception{
        mock.close();
    }

    @Test
    void create_consultation_with_success() {
        //arrange
        ConsultationDTO consultationDTO = ConsultationDtoHelper.defaultDTO();
        Patient patient = PatientHelper.createPatientWithId();
        Nurse nurse = NurseHelper.createNurseWithId();
        Doctor doctor = DoctorHelper.createDoctorWithId();
        Appointment appointment = AppointmentHelper.defaultDTO();
        Consultation expectedResult = ConsultationHelper.createConsultationWithId();

        when(patientGateway.findPatientByCpf(consultationDTO.cpf())).thenReturn(Optional.of(patient));
        when(nurseGateway.findNurseByCip(consultationDTO.cip())).thenReturn(Optional.of(nurse));
        when(doctorGateway.findDoctorByCrm(consultationDTO.crm())).thenReturn(Optional.of(doctor));
        doNothing().when(appointmentEventGateway).publishAppointmentCreated(appointment);
        when(consultationGateway.save(any(Consultation.class))).thenReturn(expectedResult);



        //act
        var savedConsultation = createConsultationUseCase.execute(consultationDTO);

        //assert
        ArgumentCaptor<Consultation> consultationCaptor = ArgumentCaptor.forClass(Consultation.class);
        verify(consultationGateway, times(1)).save(consultationCaptor.capture());

        Consultation capturedConsultation = consultationCaptor.getValue();

        assertThat(capturedConsultation.getDoctor().getCrm()).isEqualTo(expectedResult.getDoctor().getCrm());
        assertThat(capturedConsultation.getPatient().getName()).isEqualTo(expectedResult.getPatient().getName());
        assertThat(savedConsultation).isNotNull();

    }
}
