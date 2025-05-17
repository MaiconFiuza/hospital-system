package com.fiuza.appointment_scheduling.core.usecases.nurse;

import com.fiuza.appointment_scheduling.core.dto.request.ConsultationDTO;
import com.fiuza.appointment_scheduling.core.dto.request.NurseDTO;
import com.fiuza.appointment_scheduling.core.dto.response.Appointment;
import com.fiuza.appointment_scheduling.core.entities.consulation.Consultation;
import com.fiuza.appointment_scheduling.core.entities.doctor.Doctor;
import com.fiuza.appointment_scheduling.core.entities.nurse.Nurse;
import com.fiuza.appointment_scheduling.core.gateway.*;
import com.fiuza.appointment_scheduling.helper.dto.NurseDtoHelper;
import com.fiuza.appointment_scheduling.helper.entities.nurse.NurseHelper;
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
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
public class CreateNurseUseCaseTest {
    @Mock
    NurseGateway nurseGateway;

    @InjectMocks
    CreateNurseUseCase createNurseUseCase;

    AutoCloseable mock;

    @BeforeEach
    void setup() {
        mock = MockitoAnnotations.openMocks(this);
        createNurseUseCase = new CreateNurseUseCase(nurseGateway);
    }

    @AfterEach
    void teardown() throws Exception{
        mock.close();
    }

    @Test
    void create_nurse_with_success() {
        //arrange
        NurseDTO nurseDTO = NurseDtoHelper.defaultDto();
        Nurse nurseResult = NurseHelper.createNurseWithId();


        when(nurseGateway.findNurseByCipValidation(nurseDTO.cip())).thenReturn(false);
        when(nurseGateway.save(any(Nurse.class))).thenReturn(nurseResult);

        //act
        var savedNurse = createNurseUseCase.execute(nurseDTO);

        //assert
        ArgumentCaptor<Nurse> nurseCaptor = ArgumentCaptor.forClass(Nurse.class);
        verify(nurseGateway, times(1)).save(nurseCaptor.capture());

        Nurse capturedNurse = nurseCaptor.getValue();

        assertThat(capturedNurse.getCip()).isEqualTo(nurseResult.getCip());
        assertThat(capturedNurse.getName()).isEqualTo(nurseResult.getName());
        assertThat(savedNurse).isNotNull();

    }
}
