package com.fiuza.appointment_scheduling.infra.adapter;

import com.example.shared.entities.DoctorModel;
import com.fiuza.appointment_scheduling.core.entities.doctor.Doctor;
import com.fiuza.appointment_scheduling.core.exceptions.NotFoundException;
import com.fiuza.appointment_scheduling.core.gateway.DoctorGateway;
import com.fiuza.appointment_scheduling.infra.mappers.DoctorMapper;
import com.fiuza.appointment_scheduling.infra.repository.DoctorRepository;

import java.util.Optional;

public class DoctorRepositoryImp implements DoctorGateway {
    private final DoctorRepository doctorRepository;

    public DoctorRepositoryImp(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    @Override
    public Doctor save(Doctor doctor) {
        DoctorModel doctorModel = DoctorMapper.doctorToDoctorModel(doctor);
        var doctorSaved = doctorRepository.save(doctorModel);
        return DoctorMapper.doctorModelToDoctor(doctorSaved);
    }

    @Override
    public Optional<Doctor> findDoctorById(Long id) {
        var doctorModel = doctorRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Doutor(a) não encontrado(a)"));
        return Optional.of(DoctorMapper.doctorModelToDoctor(doctorModel));
    }

    @Override
    public Optional<Doctor> findDoctorByCrm(String crm) {
        var doctorModel = doctorRepository.findByCrm(crm).orElseThrow(
                () -> new NotFoundException("Doutor(a) não encontrado(a)"));
        return Optional.of(DoctorMapper.doctorModelToDoctor(doctorModel));
    }

    @Override
    public boolean findDoctorByCrmValidation(String crm) {
        return doctorRepository.findByCrm(crm).isPresent();
    }


}
