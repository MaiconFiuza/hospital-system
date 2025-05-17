package com.fiuza.appointment_scheduling.core.usecases.patient;

import com.fiuza.appointment_scheduling.core.dto.request.NurseDTO;
import com.fiuza.appointment_scheduling.core.dto.request.PatientDTO;
import com.fiuza.appointment_scheduling.core.entities.nurse.Nurse;
import com.fiuza.appointment_scheduling.core.entities.patient.Patient;
import com.fiuza.appointment_scheduling.core.exceptions.AlreadyExistException;
import com.fiuza.appointment_scheduling.core.gateway.NurseGateway;
import com.fiuza.appointment_scheduling.core.gateway.PatientGateway;
import com.fiuza.appointment_scheduling.core.usecases.nurse.CreateNurseUseCase;
import com.fiuza.appointment_scheduling.helper.dto.NurseDtoHelper;
import com.fiuza.appointment_scheduling.helper.dto.PatientDtoHelper;
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

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CreatePatientUseCaseTest {
    @Mock
    PatientGateway patientGateway;

    @InjectMocks
    CreatePatientUseCase createPatientUseCase;

    AutoCloseable mock;

    @BeforeEach
    void setup() {
        mock = MockitoAnnotations.openMocks(this);
        createPatientUseCase = new CreatePatientUseCase(patientGateway);
    }

    @AfterEach
    void teardown() throws Exception{
        mock.close();
    }

    @Test
    void create_patient_with_success() {
        //arrange
        PatientDTO patientDTO = PatientDtoHelper.defaultDto();
        Patient patientResult = PatientHelper.createPatientWithId();


        when(patientGateway.findPatientByCpfValidation(patientDTO.cpf())).thenReturn(false);
        when(patientGateway.save(any(Patient.class))).thenReturn(patientResult);

        //act
        var savedPatient = createPatientUseCase.execute(patientDTO);

        //assert
        ArgumentCaptor<Patient> patientCaptor = ArgumentCaptor.forClass(Patient.class);
        verify(patientGateway, times(1)).save(patientCaptor.capture());

        Patient capturedPatient = patientCaptor.getValue();

        assertThat(capturedPatient.getCpf()).isEqualTo(patientResult.getCpf());
        assertThat(capturedPatient.getName()).isEqualTo(patientResult.getName());
        assertThat(savedPatient).isNotNull();
    }

    @Test
    void create_patient_should_fail_with_already_exist() {
        //arrange
        PatientDTO patientDTO = PatientDtoHelper.defaultDto();

        when(patientGateway.findPatientByCpfValidation(patientDTO.cpf())).thenReturn(true);

        // act
        assertThatThrownBy(
                () -> createPatientUseCase.execute(patientDTO))
                .isInstanceOf(AlreadyExistException.class)
                .hasMessage("Número de cpf já está em uso");

        // assert
        verify(patientGateway, times(0)).save(any(Patient.class));
    }
}
