package com.fiuza.query_history.core.usecase;

import com.fiuza.query_history.core.entities.Consultation;
import com.fiuza.query_history.core.gateway.ConsultationGateway;
import com.fiuza.query_history.core.usecases.GetConsultationUseCase;
import com.fiuza.query_history.helper.entities.consultation.ConsultationHelper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
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
        var cpf = consultation.getPatient().getCpf();

        when(consultationGateway.findConsultationsByCpf(cpf))
                .thenReturn(List.of(consultation));

        //act
        var consultationResult = getConsultationUseCase.execute(cpf);

        //assert
        assertThat(consultationResult).isNotNull();

    }
}
