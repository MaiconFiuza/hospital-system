package com.fiuza.appointment_scheduling.core.presenters;

import com.fiuza.appointment_scheduling.core.dto.response.ConsultationDoctorResponseDTO;
import com.fiuza.appointment_scheduling.core.dto.response.ConsultationNurseResponseDTO;
import com.fiuza.appointment_scheduling.core.dto.response.ConsultationPatientResponseDTO;
import com.fiuza.appointment_scheduling.core.dto.response.ConsultationResponseDTO;
import com.fiuza.appointment_scheduling.core.entities.consulation.Consultation;

public class ConsultationPresenter {

    public static ConsultationResponseDTO toConsultationResponseDto(Consultation consultation) {
        ConsultationDoctorResponseDTO consultationDoctorResponseDTO = new ConsultationDoctorResponseDTO(
                consultation.getDoctor().getCrm(),
                consultation.getDoctor().getName()
        );

        ConsultationNurseResponseDTO consultationNurseResponseDTO = new ConsultationNurseResponseDTO(
                consultation.getNurse().getCip(),
                consultation.getNurse().getName()
        );

        ConsultationPatientResponseDTO consultationPatientResponseDTO = new ConsultationPatientResponseDTO(
                consultation.getPatient().getCpf(),
                consultation.getPatient().getName()
        );

        return new ConsultationResponseDTO(
                consultation.getId(),
                consultationDoctorResponseDTO,
                consultationNurseResponseDTO,
                consultationPatientResponseDTO,
                consultation.getDateAndHour(),
                consultation.getSpecialty()
        );
    }
}
