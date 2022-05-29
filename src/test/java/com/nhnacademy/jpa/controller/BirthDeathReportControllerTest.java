package com.nhnacademy.jpa.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nhnacademy.jpa.dto.request.birthdeathreport.BirthReportInsertRequest;
import com.nhnacademy.jpa.dto.request.birthdeathreport.BirthReportModifyRequest;
import com.nhnacademy.jpa.service.BirthDeathReportService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

class BirthDeathReportControllerTest {

    MockMvc mockMvc;
    BirthDeathReportService service;
    ObjectMapper mapper;

    @BeforeEach
    void setUp() {
        mapper = new ObjectMapper();
        service = Mockito.mock(BirthDeathReportService.class);
        mockMvc = MockMvcBuilders.standaloneSetup(new BirthDeathReportController(service))
                                 .build();
    }

    @Test
    @DisplayName("출생신고")
    void birthInsert() throws Exception {

        BirthReportInsertRequest request = new BirthReportInsertRequest();

        mockMvc.perform(post("/residents/{serialNumber}/birth", "0")
                   .contentType(MediaType.APPLICATION_JSON)
                   .content(mapper.writeValueAsString(request)))
               .andExpect(status().isCreated())
               .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
               .andDo(print());
    }

    @Test
    @DisplayName("출생신고 수정")
    void birthModify() throws Exception {

        BirthReportModifyRequest request = new BirthReportModifyRequest();

        mockMvc.perform(put("/residents/{serialNumber}/birth/{targetSerialNumber}", "0", "1")
                   .contentType(MediaType.APPLICATION_JSON)
                   .content(mapper.writeValueAsString(request)))
               .andExpect(status().isOk())
               .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
               .andDo(print());
    }

    @Test
    @DisplayName("출생신고 삭제")
    void birthDelete() throws Exception {

        mockMvc.perform(delete("/residents/{serialNumber}/birth/{targetSerialNumber}", "0", "1"))
               .andExpect(status().isNoContent());
    }
}