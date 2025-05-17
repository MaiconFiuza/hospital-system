package com.fiuza.appointment_scheduling.application;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fiuza.appointment_scheduling.application.controllers.ConsultationController;
import com.fiuza.appointment_scheduling.application.handlers.ControllerExceptionHandler;
import com.fiuza.appointment_scheduling.core.dto.request.ConsultationDTO;
import com.fiuza.appointment_scheduling.core.entities.consulation.Consultation;
import com.fiuza.appointment_scheduling.core.exceptions.InternalServerError;
import com.fiuza.appointment_scheduling.core.exceptions.NotFoundException;
import com.fiuza.appointment_scheduling.core.usecases.consultation.CreateConsultationUseCase;
import com.fiuza.appointment_scheduling.core.usecases.consultation.DeleteConsultationUseCase;
import com.fiuza.appointment_scheduling.core.usecases.consultation.GetConsultationUseCase;
import com.fiuza.appointment_scheduling.core.usecases.consultation.UpdateConsultationUseCase;
import com.fiuza.appointment_scheduling.helper.dto.ConsultationDtoHelper;
import com.fiuza.appointment_scheduling.helper.entities.consultation.ConsultationHelper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class ConsultationControllerTest {
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Mock
    CreateConsultationUseCase createConsultationUseCase;

    @Mock
    UpdateConsultationUseCase updateConsultationUseCase;

    @Mock
    DeleteConsultationUseCase deleteConsultationUseCase;

    @Mock
    GetConsultationUseCase getConsultationUseCase;

    @Mock
    private MockMvc mockMvc;

    @InjectMocks
    ConsultationController consultationController;

    AutoCloseable mock;

    @BeforeEach
    void setup() {
        objectMapper.registerModule(new JavaTimeModule());
        mock = MockitoAnnotations.openMocks(this);
        consultationController = new ConsultationController(
                createConsultationUseCase,
                updateConsultationUseCase,
                deleteConsultationUseCase,
                getConsultationUseCase
        );
        mockMvc = MockMvcBuilders.standaloneSetup(consultationController)
                .setControllerAdvice(new ControllerExceptionHandler())
                .addFilter((request, response, chain) -> {
                    response.setCharacterEncoding("UTF-8");
                    chain.doFilter(request, response);
                }, "/*")
                .build();
    }

    @AfterEach
    void teardown() throws Exception{
        mock.close();
    }

    @Test
    public void create_consultation_with_success() throws Exception {
        //arrange
        ConsultationDTO consultationDTO = ConsultationDtoHelper.defaultDTO();
        Consultation consultation = ConsultationHelper.createConsultationWithId();

        when(createConsultationUseCase.execute(any(ConsultationDTO.class))).thenReturn(consultation);

        //act & assert
        mockMvc.perform(post("/consultation")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(consultationDTO)))
                .andExpect(status().isCreated());

        verify(createConsultationUseCase, times(1)).execute(any(ConsultationDTO.class));
    }

    @Test
    public void create_consultation_should_fail_not_found_exception() throws Exception {
        //arrange
        ConsultationDTO consultationDTO = ConsultationDtoHelper.incorrectCpfDTO();

        when(createConsultationUseCase.execute(consultationDTO))
                .thenThrow(new NotFoundException("Paciente não econtrado por favor verifique o CPF digitado"));

        //act & assert
        mockMvc.perform(post("/consultation")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(consultationDTO)))
                .andExpect(status().isNotFound());

        verify(createConsultationUseCase, times(1)).execute(any(ConsultationDTO.class));
    }

    @Test
    public void create_consultation_should_fail_with_internal_server_error() throws Exception {
        //arrange
        ConsultationDTO consultationDTO = ConsultationDtoHelper.defaultDTO();

        when(createConsultationUseCase.execute(consultationDTO))
                .thenThrow(new InternalServerError("Erro interno, por favor tentar novamente"));

        //act & assert
        mockMvc.perform(post("/consultation")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(consultationDTO)))
                .andExpect(status().isInternalServerError());

        verify(createConsultationUseCase, times(1)).execute(any(ConsultationDTO.class));
    }

    @Test
    public void create_consultation_should_fail_with_null_data_not_null() throws Exception {
        //arrange
        ConsultationDTO consultationDTO = ConsultationDtoHelper.withoutCpfDTO();

        when(createConsultationUseCase.execute(consultationDTO))
                .thenThrow(new InternalServerError("Todos os campos devem ser enviados"));

        //act & assert
        mockMvc.perform(post("/consultation")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(consultationDTO)))
                .andExpect(status().isInternalServerError());

        verify(createConsultationUseCase, times(1)).execute(any(ConsultationDTO.class));
    }
}
