package com.fiuza.query_history.application.controller;

import com.fiuza.query_history.core.dto.response.ConsultationResponseDTO;
import com.fiuza.query_history.core.entities.Consultation;
import com.fiuza.query_history.core.usecases.GetConsultationUseCase;
import com.fiuza.query_history.helper.entities.consultation.ConsultationHelper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.graphql.GraphQlTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.graphql.test.tester.GraphQlTester;

import java.util.List;


@GraphQlTest(ConsultationController.class)
public class ConsultationControllerTest {

    @Autowired
    private GraphQlTester graphQlTester;

    @MockBean
    private GetConsultationUseCase getConsultationUseCase;

    @Test
    void shouldReturnConsultationsByCpf() {
        //arrange
        Consultation consultation = ConsultationHelper.createConsultationWithId();
        var cpf = consultation.getPatient().getCpf();
        List<Consultation> consultations = List.of(
                consultation
        );


        Mockito.when(getConsultationUseCase.execute(cpf)).thenReturn(consultations);

        // Act & Assert
        graphQlTester.documentName("findConsultationsByCpf")
                .variable("cpf", cpf)
                .execute()
                .path("findConsultationsByCpf")
                .entityList(ConsultationResponseDTO.class)
                .hasSize(1);
    }

}
