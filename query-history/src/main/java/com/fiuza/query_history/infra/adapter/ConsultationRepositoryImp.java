package com.fiuza.query_history.infra.adapter;

import com.example.shared.entities.ConsultationModel;
import com.fiuza.query_history.core.entities.Consultation;
import com.fiuza.query_history.core.gateway.ConsultationGateway;
import com.fiuza.query_history.infra.mapper.ConsultationMapper;
import com.fiuza.query_history.infra.repository.ConsultationRepository;

import java.util.List;

public class ConsultationRepositoryImp implements ConsultationGateway {

    private final ConsultationRepository consultationRepository;

    public ConsultationRepositoryImp(ConsultationRepository consultationRepository) {
        this.consultationRepository = consultationRepository;
    }

    @Override
    public List<Consultation> findConsultationsByCpf(String cpf) {
        List<ConsultationModel> consultationModels = consultationRepository.findByPatientCpf(cpf);
        return ConsultationMapper.consultationModelListToConsultationList(consultationModels);
    }
}
