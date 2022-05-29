package com.nhnacademy.jpa.controller;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nhnacademy.jpa.dto.request.household.HouseholdRequest;
import com.nhnacademy.jpa.service.HouseholdService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

class HouseholdControllerTest {

    MockMvc mockMvc;
    HouseholdService service;

    @BeforeEach
    void setUp() {
        service = mock(HouseholdService.class);
        mockMvc = MockMvcBuilders.standaloneSetup(new HouseholdController(service))
                                 .build();
    }

    @Test
    @DisplayName("세대 추가 요청")
    void insert() throws Exception {

        ObjectMapper mapper = new ObjectMapper();
        HouseholdRequest householdRequest = new HouseholdRequest();

        mockMvc.perform(post("/household")
                   .contentType(MediaType.APPLICATION_JSON)
                   .content(mapper.writeValueAsString(householdRequest)))
               .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
               .andExpect(status().isCreated());
    }

    @Test
    @DisplayName("세대 삭제")
    void delete() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.delete("/household/{householdSerialNumber}", "0"))
               .andExpect(status().isNoContent());
    }
}