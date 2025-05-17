package com.fiuza.query_history.infra.repository;

import com.example.shared.entities.ConsultationModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ConsultationRepository extends JpaRepository<ConsultationModel, Long>{

    List<ConsultationModel> findByPatientCpf(String cpf);
}
