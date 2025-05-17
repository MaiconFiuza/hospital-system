package com.fiuza.appointment_scheduling.core.usecases;

import com.fiuza.appointment_scheduling.core.dto.request.DoctorDTO;
import com.fiuza.appointment_scheduling.core.dto.request.PatientDTO;
import com.fiuza.appointment_scheduling.core.entities.doctor.Doctor;
import com.fiuza.appointment_scheduling.core.entities.patient.Patient;
import com.fiuza.appointment_scheduling.core.exceptions.AlreadyExistException;
import com.fiuza.appointment_scheduling.core.gateway.DoctorGateway;
import com.fiuza.appointment_scheduling.core.gateway.PatientGateway;
import com.fiuza.appointment_scheduling.core.usecases.doctor.CreateDoctorUseCase;
import com.fiuza.appointment_scheduling.core.usecases.patient.CreatePatientUseCase;
import com.fiuza.appointment_scheduling.helper.dto.DoctorDtoHelper;
import com.fiuza.appointment_scheduling.helper.dto.PatientDtoHelper;
import com.fiuza.appointment_scheduling.helper.entities.doctor.DoctorHelper;
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
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
public class CreateDoctorUseCaseTest {
    @Mock
    DoctorGateway doctorGateway;

    @InjectMocks
    CreateDoctorUseCase createDoctorUseCase;

    AutoCloseable mock;

    @BeforeEach
    void setup() {
        mock = MockitoAnnotations.openMocks(this);
        createDoctorUseCase = new CreateDoctorUseCase(doctorGateway);
    }

    @AfterEach
    void teardown() throws Exception{
        mock.close();
    }

    @Test
    void create_doctor_with_success() {
        //arrange
        DoctorDTO doctorDTO = DoctorDtoHelper.defaultDTO();
        Doctor doctorResult = DoctorHelper.createDoctorWithId();

        when(doctorGateway.findDoctorByCrmValidation(doctorDTO.crm())).thenReturn(false);
        when(doctorGateway.save(any(Doctor.class))).thenReturn(doctorResult);

        //act
        var savedDoctor = createDoctorUseCase.execute(doctorDTO);

        //assert
        ArgumentCaptor<Doctor> doctorCaptor = ArgumentCaptor.forClass(Doctor.class);
        verify(doctorGateway, times(1)).save(doctorCaptor.capture());

        Doctor capturedDoctor = doctorCaptor.getValue();

        assertThat(capturedDoctor.getCrm()).isEqualTo(doctorResult.getCrm());
        assertThat(capturedDoctor.getName()).isEqualTo(doctorResult.getName());
        assertThat(savedDoctor).isNotNull();
    }

    @Test
    void create_doctor_should_fail_with_already_exist() {
        //arrange
        DoctorDTO doctorDTO = DoctorDtoHelper.defaultDTO();

        when(doctorGateway.findDoctorByCrmValidation(doctorDTO.crm())).thenReturn(true);

        // act
        assertThatThrownBy(
                () -> createDoctorUseCase.execute(doctorDTO))
                .isInstanceOf(AlreadyExistException.class)
                .hasMessage("Médico já possuí cadastro na nossa rede");

        // assert
        verify(doctorGateway, times(0)).save(any(Doctor.class));
    }
}
