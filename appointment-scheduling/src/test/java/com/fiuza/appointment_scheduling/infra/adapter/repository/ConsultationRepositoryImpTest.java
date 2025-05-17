package com.fiuza.appointment_scheduling.infra.adapter.repository;

import com.example.shared.entities.ConsultationModel;
import com.example.shared.entities.DoctorModel;
import com.example.shared.entities.helper.ConsultationModelHelper;
import com.example.shared.entities.helper.DoctorModelHelper;
import com.fiuza.appointment_scheduling.core.entities.consulation.Consultation;
import com.fiuza.appointment_scheduling.core.entities.doctor.Doctor;
import com.fiuza.appointment_scheduling.helper.entities.consultation.ConsultationHelper;
import com.fiuza.appointment_scheduling.helper.entities.doctor.DoctorHelper;
import com.fiuza.appointment_scheduling.infra.adapter.ConsultationRepositoryImp;
import com.fiuza.appointment_scheduling.infra.repository.ConsultationRepository;
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
public class ConsultationRepositoryImpTest {
    @Mock
    private ConsultationRepository consultationRepository;

    @InjectMocks
    ConsultationRepositoryImp consultationRepositoryImp;

    AutoCloseable mock;

    @BeforeEach
    void setup() {
        mock = MockitoAnnotations.openMocks(this);
        consultationRepositoryImp = new ConsultationRepositoryImp(consultationRepository);
    }

    @AfterEach
    void teardown() throws Exception {
        mock.close();
    }

    @Test
    void save_sucess() {
        //arrange
        Consultation consultationDefault = ConsultationHelper.createConsultationDefault();
        ConsultationModel consultationModel = ConsultationModelHelper.createConsultationDefault();
        Consultation consultationResult = ConsultationHelper.createConsultationWithId();

        when(consultationRepository.save(any(ConsultationModel.class))).thenReturn(consultationModel);

        //act
        var savedConsultation = consultationRepositoryImp.save(consultationDefault);

        //assert
        ArgumentCaptor<ConsultationModel> consultationCaptor = ArgumentCaptor.forClass(ConsultationModel.class);
        verify(consultationRepository, times(1)).save(consultationCaptor.capture());

        ConsultationModel capturedConsultation = consultationCaptor.getValue();

        assertThat(capturedConsultation.getDoctor().getCrm()).isEqualTo(consultationResult.getDoctor().getCrm());
        assertThat(savedConsultation).isNotNull();
    }
}
