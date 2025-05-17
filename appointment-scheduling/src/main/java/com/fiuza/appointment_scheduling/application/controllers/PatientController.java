package com.fiuza.appointment_scheduling.application.controllers;

import com.fiuza.appointment_scheduling.core.dto.request.PatientDTO;
import com.fiuza.appointment_scheduling.core.entities.patient.Patient;
import com.fiuza.appointment_scheduling.core.usecases.patient.CreatePatientUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/patient")
@Tag(name = "Patient", description = "Crud para gerenciamento de Paciente")
public class PatientController {
    private static  final Logger logger = LoggerFactory.getLogger(PatientController.class);

    private final CreatePatientUseCase createPatientUseCase;

    public PatientController(
       CreatePatientUseCase createPatientUseCase
    ) {
        this.createPatientUseCase = createPatientUseCase;
    }

    @Operation(
            description = "Cria conta do Paciente",
            summary = "Endpoint responsável por criar o Paciente",
            responses = {
                    @ApiResponse(description = "CREATED", responseCode = "201")
            }
    )
    @PostMapping
    public ResponseEntity<Patient> createPatient(@RequestBody PatientDTO patientDTO) {
        logger.info("POST /patient");
        var result = createPatientUseCase.execute(patientDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }
}
