package com.fiuza.appointment_scheduling.infra.adapter;

import com.example.shared.entities.PatientModel;
import com.fiuza.appointment_scheduling.core.entities.patient.Patient;
import com.fiuza.appointment_scheduling.core.exceptions.NotFoundException;
import com.fiuza.appointment_scheduling.core.gateway.PatientGateway;
import com.fiuza.appointment_scheduling.infra.mappers.PatientMapper;
import com.fiuza.appointment_scheduling.infra.repository.PatientRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class PatientRepositoryImp implements PatientGateway {

    private final PatientRepository patientRepository;

    public PatientRepositoryImp(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }


    @Override
    public Patient save(Patient patient) {
        PatientModel patientModel = PatientMapper.patientToPatientModel(patient);
        var patientSaved = patientRepository.save(patientModel);
        return PatientMapper.patientModelToPatient(patientSaved);

    }

    @Override
    public Optional<Patient> findPatientById(Long id) {
        var patientModel = patientRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Paciente não encontrado"));

        return Optional.of(PatientMapper.patientModelToPatient(patientModel));
    }

    @Override
    public Optional<Patient> findPatientByCpf(String cpf) {
        var patientModel = patientRepository.findByCpf(cpf).orElseThrow(
                () -> new NotFoundException("Paciente não encontrado"));

        return Optional.of(PatientMapper.patientModelToPatient(patientModel));
    }

    @Override
    public boolean findPatientByCpfValidation(String cpf) {
        return patientRepository.findByCpf(cpf).isPresent();
    }

}
