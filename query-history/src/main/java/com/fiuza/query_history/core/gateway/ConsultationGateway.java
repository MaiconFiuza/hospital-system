package com.fiuza.query_history.core.gateway;

import com.fiuza.query_history.core.entities.Consultation;

import java.util.List;

public interface ConsultationGateway {
    List<Consultation> findConsultationsByCpf(String cpf);
}
