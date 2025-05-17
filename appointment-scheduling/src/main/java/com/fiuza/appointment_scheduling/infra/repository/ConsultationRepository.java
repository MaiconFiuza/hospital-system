package com.fiuza.appointment_scheduling.infra.repository;

import com.example.shared.entities.ConsultationModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConsultationRepository extends JpaRepository<ConsultationModel, Long> {
}
