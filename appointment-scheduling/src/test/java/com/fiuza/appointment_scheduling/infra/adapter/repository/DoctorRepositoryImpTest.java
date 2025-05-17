package com.fiuza.appointment_scheduling.infra.adapter.repository;

import com.example.shared.entities.DoctorModel;
import com.example.shared.entities.helper.DoctorModelHelper;
import com.fiuza.appointment_scheduling.core.entities.doctor.Doctor;
import com.fiuza.appointment_scheduling.helper.entities.doctor.DoctorHelper;
import com.fiuza.appointment_scheduling.infra.adapter.DoctorRepositoryImp;
import com.fiuza.appointment_scheduling.infra.repository.DoctorRepository;
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
public class DoctorRepositoryImpTest {
    @Mock
    private DoctorRepository doctorRepository;

    @InjectMocks
    DoctorRepositoryImp doctorRepositoryImp;

    AutoCloseable mock;

    @BeforeEach
    void setup() {
        mock = MockitoAnnotations.openMocks(this);
        doctorRepositoryImp = new DoctorRepositoryImp(doctorRepository);
    }

    @AfterEach
    void teardown() throws Exception {
        mock.close();
    }

    @Test
    void save_sucess() {
        //arrange
        Doctor doctorDefault = DoctorHelper.createDoctorDefault();
        DoctorModel doctorModel = DoctorModelHelper.createDoctorDefault();
        Doctor doctorResult = DoctorHelper.createDoctorWithId();

        when(doctorRepository.save(any(DoctorModel.class))).thenReturn(doctorModel);

        //act
        var savedNurse = doctorRepositoryImp.save(doctorDefault);

        //assert
        ArgumentCaptor<DoctorModel> doctorCaptor = ArgumentCaptor.forClass(DoctorModel.class);
        verify(doctorRepository, times(1)).save(doctorCaptor.capture());

        DoctorModel capturedDoctor = doctorCaptor.getValue();

        assertThat(capturedDoctor.getName()).isEqualTo(doctorResult.getName());
        assertThat(capturedDoctor.getCrm()).isEqualTo(doctorResult.getCrm());
        assertThat(savedNurse).isNotNull();
    }
}
