package com.fiuza.appointment_scheduling.application.controllers;

import com.fiuza.appointment_scheduling.core.dto.response.ConsultationResponseDTO;
import com.fiuza.appointment_scheduling.core.entities.consulation.Consultation;
import com.fiuza.appointment_scheduling.core.presenters.ConsultationPresenter;
import com.fiuza.appointment_scheduling.core.usecases.consultation.CreateConsultationUseCase;
import com.fiuza.appointment_scheduling.core.usecases.consultation.DeleteConsultationUseCase;
import com.fiuza.appointment_scheduling.core.usecases.consultation.GetConsultationUseCase;
import com.fiuza.appointment_scheduling.core.usecases.consultation.UpdateConsultationUseCase;
import com.fiuza.appointment_scheduling.infra.adapter.dto.ConsultationRequest;
import com.fiuza.appointment_scheduling.infra.adapter.dto.UpdateConsultation;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/consultation")
@Tag(name = "Consultation", description = "Crud para gerenciamento de consultas")
public class ConsultationController {
    private static  final Logger logger = LoggerFactory.getLogger(ConsultationController.class);

    private CreateConsultationUseCase createConsultationUseCase;
    private UpdateConsultationUseCase updateConsultationUseCase;
    private DeleteConsultationUseCase deleteConsultationUseCase;
    private GetConsultationUseCase getConsultationUseCase;

    public ConsultationController(
        CreateConsultationUseCase createConsultationUseCase,
        UpdateConsultationUseCase updateConsultationUseCase,
        DeleteConsultationUseCase deleteConsultationUseCase,
        GetConsultationUseCase getConsultationUseCase
    ) {
        this.createConsultationUseCase = createConsultationUseCase;
        this.updateConsultationUseCase = updateConsultationUseCase;
        this.deleteConsultationUseCase = deleteConsultationUseCase;
        this.getConsultationUseCase = getConsultationUseCase;
    }

    @Operation(
            description = "Busca consultas",
            summary = "Endpoint responsável para buscar as consultas",
            responses = {
                    @ApiResponse(description = "OK", responseCode = "200")
            }
    )
    @GetMapping("/{id}")
    public ResponseEntity<ConsultationResponseDTO> getConsultation(@PathVariable Long id) {
        logger.info("Get /consultation/{}", id);
        Consultation consultation = getConsultationUseCase.execute(id);
        var result = ConsultationPresenter.toConsultationResponseDto(consultation);
        return ResponseEntity.ok(result);
    }

    @Operation(
            description = "Cria consulta",
            summary = "Endpoint responsável por criar a consulta",
            responses = {
                    @ApiResponse(description = "CREATED", responseCode = "201")
            }
    )
    @PostMapping
    public ResponseEntity<ConsultationResponseDTO> createConsultation(@RequestBody ConsultationRequest consultationRequest) {
        logger.info("POST /consultation");
        Consultation consultation = createConsultationUseCase.execute(consultationRequest.toCoreDTO());
        var result = ConsultationPresenter.toConsultationResponseDto(consultation);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @Operation(
            description = "Atualiza consultas",
            summary = "Endpoint responsável para atualizar as consultas",
            responses = {
                    @ApiResponse(description = "OK", responseCode = "200")
            }
    )
    @PutMapping("/{id}")
    public ResponseEntity<ConsultationResponseDTO> updateConsultation(
            @PathVariable Long id,
            @RequestBody UpdateConsultation requestDto
    ) {
        logger.info("PUT /consultation/{}", id);
        Consultation consultation = updateConsultationUseCase.execute(requestDto.toCoreDTO(), id);
        var result = ConsultationPresenter.toConsultationResponseDto(consultation);
        return ResponseEntity.ok(result);
    }

    @Operation(
            description = "Exclui a consulta",
            summary = "Endpoint responsável pela exclusão do cadastro da consulta",
            responses = {
                    @ApiResponse(description = "NO CONTENT", responseCode = "204")
            }
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteConsultation(@PathVariable Long id) {
        logger.info("DELETE /consultation/{}", id);
        deleteConsultationUseCase.execute(id);
        return ResponseEntity.noContent().build();
    }
}
