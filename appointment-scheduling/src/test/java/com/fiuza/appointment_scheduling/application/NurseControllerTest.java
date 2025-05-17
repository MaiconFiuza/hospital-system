package com.fiuza.appointment_scheduling.application;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fiuza.appointment_scheduling.application.controllers.NurseController;
import com.fiuza.appointment_scheduling.application.handlers.ControllerExceptionHandler;
import com.fiuza.appointment_scheduling.core.dto.request.NurseDTO;
import com.fiuza.appointment_scheduling.core.entities.nurse.Nurse;
import com.fiuza.appointment_scheduling.core.usecases.nurse.CreateNurseUseCase;
import com.fiuza.appointment_scheduling.helper.dto.NurseDtoHelper;
import com.fiuza.appointment_scheduling.helper.entities.nurse.NurseHelper;
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
public class NurseControllerTest {
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Mock
    CreateNurseUseCase createNurseUseCase;

    @Mock
    private MockMvc mockMvc;

    @InjectMocks
    NurseController nurseController;

    AutoCloseable mock;

    @BeforeEach
    void setup() {
        mock = MockitoAnnotations.openMocks(this);
        nurseController = new NurseController(createNurseUseCase);
        mockMvc = MockMvcBuilders.standaloneSetup(nurseController)
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
    public void create_nurse_with_success() throws Exception {
        //arrange
        NurseDTO nurseDTO = NurseDtoHelper.defaultDto();
        Nurse nurse = NurseHelper.createNurseWithId();

        when(createNurseUseCase.execute(any(NurseDTO.class))).thenReturn(nurse);

        //act & assert
        mockMvc.perform(post("/nurse")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(nurseDTO)))
                .andExpect(status().isCreated());

        verify(createNurseUseCase, times(1)).execute(any(NurseDTO.class));

    }

}
