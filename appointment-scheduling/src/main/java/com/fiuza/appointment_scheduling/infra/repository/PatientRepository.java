package com.fiuza.appointment_scheduling.infra.repository;

import com.example.shared.entities.PatientModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PatientRepository extends JpaRepository<PatientModel, Long> {
    Optional<PatientModel> findByCpf(String cpf);
}
