package com.fiuza.appointment_scheduling.infra.repository;

import com.example.shared.entities.NurseModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface NurseRepository extends JpaRepository<NurseModel, Long> {
    Optional<NurseModel> findByCip(String cip);
}
