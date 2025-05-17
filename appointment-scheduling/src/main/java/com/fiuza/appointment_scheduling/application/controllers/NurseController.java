package com.fiuza.appointment_scheduling.application.controllers;

import com.fiuza.appointment_scheduling.core.dto.request.NurseDTO;
import com.fiuza.appointment_scheduling.core.entities.nurse.Nurse;
import com.fiuza.appointment_scheduling.core.usecases.nurse.CreateNurseUseCase;
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
@RequestMapping("/nurse")
@Tag(name = "Nurse", description = "Crud para gerenciamento de Nurse(a)")
public class NurseController {
    private static  final Logger logger = LoggerFactory.getLogger(NurseController.class);

    private final CreateNurseUseCase createNurseUseCase;

    public NurseController(CreateNurseUseCase createNurseUseCase) {
        this.createNurseUseCase = createNurseUseCase;
    }

    @Operation(
            description = "Cria conta do Enfermeiro(a)",
            summary = "Endpoint responsável por criar o Enfermeiro(a)",
            responses = {
                    @ApiResponse(description = "CREATED", responseCode = "201")
            }
    )
    @PostMapping
    public ResponseEntity<Nurse> createNurse(@RequestBody NurseDTO nurseDTO) {
        var nurse = createNurseUseCase.execute(nurseDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(nurse);
    }
}
