package com.fiuza.appointment_scheduling.infra.adapter;

import com.example.shared.entities.NurseModel;
import com.fiuza.appointment_scheduling.core.entities.nurse.Nurse;
import com.fiuza.appointment_scheduling.core.exceptions.NotFoundException;
import com.fiuza.appointment_scheduling.core.gateway.NurseGateway;
import com.fiuza.appointment_scheduling.infra.mappers.NurseMapper;
import com.fiuza.appointment_scheduling.infra.repository.NurseRepository;

import java.util.Optional;

public class NurseRepositoryImp implements NurseGateway {
    private final NurseRepository nurseRepository;

    public NurseRepositoryImp(NurseRepository nurseRepository) {
        this.nurseRepository = nurseRepository;
    }

    @Override
    public Nurse save(Nurse nurse) {
        NurseModel nurseModel = NurseMapper.nurseToNurseModel(nurse);
        var savedNurse = nurseRepository.save(nurseModel);
        return NurseMapper.nurseModelToNurse(savedNurse);
    }

    @Override
    public Optional<Nurse> findNurseById(Long id) {
        var nurseModel = nurseRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Enfermeiro(a) não encontrado(a)"));
        return Optional.of(NurseMapper.nurseModelToNurse(nurseModel));
    }

    @Override
    public Optional<Nurse> findNurseByCip(String cip) {
        var nurseModel = nurseRepository.findByCip(cip).orElseThrow(
                () -> new NotFoundException("Enfermeiro(a) não encontrado(a)"));
        return Optional.of(NurseMapper.nurseModelToNurse(nurseModel));
    }

    @Override
    public boolean findNurseByCipValidation(String cpf) {
        return nurseRepository.findByCip(cpf).isPresent();
    }

}
