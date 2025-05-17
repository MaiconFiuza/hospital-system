package com.fiuza.appointment_scheduling.infra.adapter;

import com.example.shared.entities.ConsultationModel;
import com.fiuza.appointment_scheduling.core.entities.consulation.Consultation;
import com.fiuza.appointment_scheduling.core.exceptions.NotFoundException;
import com.fiuza.appointment_scheduling.core.gateway.ConsultationGateway;
import com.fiuza.appointment_scheduling.infra.mappers.ConsultationMapper;
import com.fiuza.appointment_scheduling.infra.repository.ConsultationRepository;

import java.util.Optional;

public class ConsultationRepositoryImp implements ConsultationGateway {

    private final ConsultationRepository consultationRepository;

    public ConsultationRepositoryImp(ConsultationRepository consultationRepository) {
        this.consultationRepository = consultationRepository;
    }

    @Override
    public Consultation save(Consultation consultation) {
        ConsultationModel consultationModel = ConsultationMapper.consultationToConsultationModel(consultation);
        var consultationSaved = consultationRepository.save(consultationModel);
        return ConsultationMapper.consultationModelToConsultation(consultationSaved);
    }

    @Override
    public Optional<Consultation> findConsultationById(Long id) {
        var consultation =  consultationRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Consulta não encontrada pelo id fornecido"));
        return Optional.of(ConsultationMapper.consultationModelToConsultation(consultation));

    }

    @Override
    public Consultation update(Consultation consultation) {
        ConsultationModel consultationModel = ConsultationMapper.consultationToConsultationModel(consultation);
        var consultationUpdated = consultationRepository.save(consultationModel);
        return ConsultationMapper.consultationModelToConsultation(consultationUpdated);
    }

    @Override
    public void delete(Long id) {
        consultationRepository.deleteById(id);
    }
}
