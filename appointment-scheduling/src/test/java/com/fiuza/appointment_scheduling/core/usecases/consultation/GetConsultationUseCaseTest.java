package com.fiuza.appointment_scheduling.core.usecases.consultation;

import com.fiuza.appointment_scheduling.core.entities.consulation.Consultation;
import com.fiuza.appointment_scheduling.core.gateway.ConsultationGateway;
import com.fiuza.appointment_scheduling.helper.entities.consultation.ConsultationHelper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class GetConsultationUseCaseTest {
    @Mock
    ConsultationGateway consultationGateway;

    @InjectMocks
    GetConsultationUseCase getConsultationUseCase;

    AutoCloseable mock;

    @BeforeEach
    void setup() {
        mock = MockitoAnnotations.openMocks(this);
        getConsultationUseCase = new GetConsultationUseCase(consultationGateway);
    }

    @AfterEach
    void teardown() throws Exception{
        mock.close();
    }

    @Test
    void get_consultation_with_success() {
        //arrange
        Consultation consultation = ConsultationHelper.createConsultationWithId();

        when(consultationGateway.findConsultationById(1L)).thenReturn(Optional.of(consultation));

        //act
        var consultationResult = getConsultationUseCase.execute(1L);

        //assert
        assertThat(consultationResult).isNotNull();

    }
}
