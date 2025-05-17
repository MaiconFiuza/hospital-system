package com.fiuza.appointment_scheduling.infra.repository;

import com.example.shared.entities.DoctorModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DoctorRepository extends JpaRepository<DoctorModel, Long> {
    Optional<DoctorModel> findByCrm(String crm);
}
