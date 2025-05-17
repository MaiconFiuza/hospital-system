package com.fiuza.appointment_scheduling.infra.adapter.repository;

import com.example.shared.entities.PatientModel;
import com.example.shared.entities.helper.PatientModelHelper;
import com.fiuza.appointment_scheduling.core.entities.patient.Patient;
import com.fiuza.appointment_scheduling.helper.entities.patient.PatientHelper;
import com.fiuza.appointment_scheduling.infra.adapter.PatientRepositoryImp;
import com.fiuza.appointment_scheduling.infra.repository.PatientRepository;
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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class PatientRepositoryImpTest {

    @Mock
    private PatientRepository patientRepository;

    @InjectMocks
    PatientRepositoryImp patientRepositoryImp;

    AutoCloseable mock;

    @BeforeEach
    void setup() {
        mock = MockitoAnnotations.openMocks(this);
        patientRepositoryImp = new PatientRepositoryImp(patientRepository);
    }

    @AfterEach
    void teardown() throws Exception {
        mock.close();
    }

    @Test
    void save_sucess() {
        //arrange
        Patient patientDefault = PatientHelper.createPatientDefault();
        PatientModel patientModel = PatientModelHelper.createPatientDefault();
        Patient patientResult = PatientHelper.createPatientWithId();

        when(patientRepository.save(any(PatientModel.class))).thenReturn(patientModel);

        //act
        var savedPatient = patientRepositoryImp.save(patientDefault);

        //assert
        ArgumentCaptor<PatientModel> patientCaptor = ArgumentCaptor.forClass(PatientModel.class);
        verify(patientRepository, times(1)).save(patientCaptor.capture());

        PatientModel capturedPatient = patientCaptor.getValue();

        assertThat(capturedPatient.getName()).isEqualTo(patientResult.getName());
        assertThat(capturedPatient.getCpf()).isEqualTo(patientResult.getCpf());
        assertThat(savedPatient).isNotNull();
    }

}
