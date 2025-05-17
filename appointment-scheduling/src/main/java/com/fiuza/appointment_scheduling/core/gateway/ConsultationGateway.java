package com.fiuza.appointment_scheduling.core.gateway;

import com.fiuza.appointment_scheduling.core.entities.consulation.Consultation;

import java.util.Optional;

public interface ConsultationGateway {
    Consultation save(Consultation consultation);

    Optional<Consultation> findConsultationById(Long id);

    Consultation update(Consultation consultation);

    void delete(Long id);

}
