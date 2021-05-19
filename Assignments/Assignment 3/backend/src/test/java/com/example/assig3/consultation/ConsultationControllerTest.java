package com.example.assig3.consultation;

import com.example.assig3.BaseControllerTest;
import com.example.assig3.TestCreationFactory;
import com.example.assig3.consultation.dto.ConsultationDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;



import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;
import java.util.Optional;

import static com.example.assig3.UrlMapping.CONSULTATION;
import static org.mockito.Mockito.when;

class ConsultationControllerTest extends BaseControllerTest {

    @InjectMocks
    private ConsultationController consultationController;

    @Mock
    private ConsultationService consultationService;

    @BeforeEach
    protected void setUp() {
        super.setUp();
        MockitoAnnotations.initMocks(this);
        consultationController = new ConsultationController(consultationService);
        mockMvc = MockMvcBuilders.standaloneSetup(consultationController).build();
    }

    @Test
    void findAll() throws Exception{
        List<ConsultationDto> consultationDtos = TestCreationFactory.listOf(ConsultationDto.class);

        when(consultationService.findAllDto()).thenReturn(consultationDtos);

        ResultActions result = mockMvc.perform(get(CONSULTATION));
        result.andExpect(status().isOk())
                .andExpect(jsonContentToBe(consultationDtos));
    }
    @Test
    void create() throws Exception {
        ConsultationDto consultationDto = TestCreationFactory.object(ConsultationDto.class);

        when(consultationService.save(consultationDto)).thenReturn(consultationDto);

        ResultActions result = performPostWithRequestBody(CONSULTATION,consultationDto);
        result.andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonContentToBe(consultationDto));
    }


}