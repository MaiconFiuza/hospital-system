package com.fiuza.appointment_scheduling.application.controllers;

import com.fiuza.appointment_scheduling.core.dto.request.DoctorDTO;
import com.fiuza.appointment_scheduling.core.entities.doctor.Doctor;
import com.fiuza.appointment_scheduling.core.usecases.doctor.CreateDoctorUseCase;
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
@RequestMapping("/doctor")
@Tag(name = "Doctor", description = "Crud para gerenciamento de Doutor(a)")
public class DoctorController {
    private static  final Logger logger = LoggerFactory.getLogger(DoctorController.class);

    private CreateDoctorUseCase createDoctorUseCase;

    public DoctorController(CreateDoctorUseCase createDoctorUseCase) {
        this.createDoctorUseCase = createDoctorUseCase;
    }

    @Operation(
            description = "Cria conta do Doutor(a)",
            summary = "Endpoint responsável por criar o Doutor(a)",
            responses = {
                    @ApiResponse(description = "CREATED", responseCode = "201")
            }
    )
    @PostMapping
    public ResponseEntity<Doctor> create(@RequestBody DoctorDTO doctorDTO) {
        logger.info("POST /doctor");
        Doctor result = createDoctorUseCase.execute(doctorDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }
}
