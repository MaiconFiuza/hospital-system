package com.fiuza.query_history.application.controller;

import com.fiuza.query_history.core.dto.response.ConsultationResponseDTO;
import com.fiuza.query_history.core.presenter.ConsultationPresenter;
import com.fiuza.query_history.core.usecases.GetConsultationUseCase;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class ConsultationController {
    private GetConsultationUseCase getConsultationUseCase;

    public ConsultationController(GetConsultationUseCase getConsultationUseCase) {
        this.getConsultationUseCase = getConsultationUseCase;
    }

    @QueryMapping(name = "findConsultationsByCpf")
    public List<ConsultationResponseDTO> getAllConsultationsByCpf(@Argument String cpf) {
        var result = getConsultationUseCase.execute(cpf);
        return ConsultationPresenter.toConsultationResponseDtoList(result);
    }

}
