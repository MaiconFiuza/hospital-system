package com.fiuza.appointment_scheduling.infra.adapter.repository;

import com.example.shared.entities.NurseModel;
import com.example.shared.entities.helper.NurseModelHelper;
import com.fiuza.appointment_scheduling.core.entities.nurse.Nurse;
import com.fiuza.appointment_scheduling.helper.entities.nurse.NurseHelper;
import com.fiuza.appointment_scheduling.infra.adapter.NurseRepositoryImp;
import com.fiuza.appointment_scheduling.infra.repository.NurseRepository;
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

@ExtendWith(MockitoExtension.class)
public class NurseRepositoryImpTest {

    @Mock
    private NurseRepository nurseRepository;

    @InjectMocks
    NurseRepositoryImp nurseRepositoryImp;

    AutoCloseable mock;

    @BeforeEach
    void setup() {
        mock = MockitoAnnotations.openMocks(this);
        nurseRepositoryImp = new NurseRepositoryImp(nurseRepository);
    }

    @AfterEach
    void teardown() throws Exception {
        mock.close();
    }

    @Test
    void save_sucess() {
        //arrange
        Nurse nurseDefault = NurseHelper.createNurseDefault();
        NurseModel nurseModel = NurseModelHelper.createNurseDefault();
        Nurse nurseResult = NurseHelper.createNurseWithId();

        when(nurseRepository.save(any(NurseModel.class))).thenReturn(nurseModel);

        //act
        var savedNurse = nurseRepositoryImp.save(nurseDefault);

        //assert
        ArgumentCaptor<NurseModel> nurseCaptor = ArgumentCaptor.forClass(NurseModel.class);
        verify(nurseRepository, times(1)).save(nurseCaptor.capture());

        NurseModel capturedNurse = nurseCaptor.getValue();

        assertThat(capturedNurse.getName()).isEqualTo(nurseResult.getName());
        assertThat(capturedNurse.getCip()).isEqualTo(nurseResult.getCip());
        assertThat(savedNurse).isNotNull();
    }

}
