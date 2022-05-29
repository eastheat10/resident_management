package com.nhnacademy.jpa.controller;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nhnacademy.jpa.dto.request.household.HouseholdRequest;
import com.nhnacademy.jpa.dto.request.householdmovementaddress.HouseholdMovementAddressRequest;
import com.nhnacademy.jpa.dto.response.householdmovementaddress.HouseholdMovementAddressResponse;
import com.nhnacademy.jpa.service.HouseholdService;
import java.time.LocalDate;
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
    ObjectMapper mapper;

    @BeforeEach
    void setUp() {
        service = mock(HouseholdService.class);
        mockMvc = MockMvcBuilders.standaloneSetup(new HouseholdController(service))
                                 .build();
        mapper = new ObjectMapper();
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

    @Test
    @DisplayName("세대이전정보 추가 요청")
    void movementInsert() throws Exception {

        HouseholdMovementAddressRequest request = new HouseholdMovementAddressRequest();

        mockMvc.perform(post("/household/{householdSerialNumber}/movement", "0")
                   .contentType(MediaType.APPLICATION_JSON)
                   .content(mapper.writeValueAsString(request)))
               .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
               .andExpect(status().isCreated());
    }

    @Test
    @DisplayName("세대이전정보 수정 요청")
    void movementModify() throws Exception {

        HouseholdMovementAddressRequest request = new HouseholdMovementAddressRequest();
        HouseholdMovementAddressResponse response = mock(HouseholdMovementAddressResponse.class);

        when(service.updateMovementAddress(
            any(request.getClass()), anyLong(), any(LocalDate.class))).thenReturn(response);

        mockMvc.perform(
                   put("/household/{householdSerialNumber}/movement/{reportDate}", "0", "20220530")
                       .contentType(MediaType.APPLICATION_JSON)
                       .content(mapper.writeValueAsString(request)))
               .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
               .andExpect(status().isOk());
    }

    @Test
    @DisplayName("세대이전정보 삭제")
    void movementDelete() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.delete(
                   "/household/{householdSerialNumber}/movement/{reportDate}", "0", "20220530"))
               .andExpect(status().isNoContent());
    }
}