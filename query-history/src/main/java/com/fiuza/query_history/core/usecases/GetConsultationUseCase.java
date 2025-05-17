package com.fiuza.query_history.core.usecases;

import com.fiuza.query_history.core.entities.Consultation;
import com.fiuza.query_history.core.gateway.ConsultationGateway;

import java.util.List;

public class GetConsultationUseCase {
    private final ConsultationGateway consultationGateway;

    public GetConsultationUseCase(ConsultationGateway consultationGateway) {
        this.consultationGateway = consultationGateway;
    }

    public List<Consultation> execute(String cpf) {return consultationGateway.findConsultationsByCpf(cpf);}
}
