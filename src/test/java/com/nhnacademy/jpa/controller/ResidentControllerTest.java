package com.nhnacademy.jpa.controller;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nhnacademy.jpa.dto.request.resident.ResidentRequest;
import com.nhnacademy.jpa.dto.response.resident.ResidentResponse;
import com.nhnacademy.jpa.service.ResidentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

class ResidentControllerTest {

    MockMvc mockMvc;
    ResidentService service;
    ObjectMapper mapper;

    @BeforeEach
    void setUp() {
        service = mock(ResidentService.class);
        mockMvc = MockMvcBuilders.standaloneSetup(new ResidentController(service))
                                 .build();
        mapper = new ObjectMapper();
    }

    @Test
    @DisplayName("주민 등록")
    void insert() throws Exception {

        ResidentRequest request = new ResidentRequest();
        ResidentResponse response = mock(ResidentResponse.class);
        when(service.insert(request)).thenReturn(response);

        mockMvc.perform(post("/residents")
                   .contentType(MediaType.APPLICATION_JSON)
                   .content(mapper.writeValueAsString(request)))
               .andExpect(status().isCreated())
               .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
               .andDo(print());
    }

    @Test
    @DisplayName("주민 수정")
    void modify() throws Exception {

        ResidentRequest request = new ResidentRequest();
        ResidentResponse response = mock(ResidentResponse.class);

        when(service.modify(request, 0L)).thenReturn(response);

        mockMvc.perform(put("/residents/{serialNumber}", "0")
                   .contentType(MediaType.APPLICATION_JSON)
                   .content(mapper.writeValueAsString(request)))
               .andExpect(status().isOk())
               .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
               .andDo(print());
    }
}