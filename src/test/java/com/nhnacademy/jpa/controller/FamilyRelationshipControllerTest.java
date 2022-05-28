package com.nhnacademy.jpa.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nhnacademy.jpa.dto.request.family_relationship.FamilyRelationshipInsertRequest;
import com.nhnacademy.jpa.dto.request.family_relationship.FamilyRelationshipRequest;
import com.nhnacademy.jpa.dto.response.FamilyRelationshipResponse;
import com.nhnacademy.jpa.service.FamilyRelationshipService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

class FamilyRelationshipControllerTest {

    MockMvc mockMvc;

    FamilyRelationshipService familyRelationshipService;

    @BeforeEach
    void setUp() {
        familyRelationshipService = mock(FamilyRelationshipService.class);
        mockMvc = MockMvcBuilders.standaloneSetup(
                                     new FamilyRelationshipController(familyRelationshipService))
                                 .build();
    }

    @Test
    @DisplayName("가족관계 추가")
    void insert() throws Exception {

        FamilyRelationshipRequest relationshipRequest = mock(FamilyRelationshipRequest.class);
        FamilyRelationshipResponse familyRelationshipResponse =
            new FamilyRelationshipResponse(0L, 0L, "");

        when(familyRelationshipService.insertFamilyRelationship(relationshipRequest))
            .thenReturn(familyRelationshipResponse);

        ObjectMapper objectMapper = new ObjectMapper();
        FamilyRelationshipInsertRequest request = new FamilyRelationshipInsertRequest(0L, "");

        mockMvc.perform(post("/residents/{serialNumber}/relationship", "0")
                   .contentType(MediaType.APPLICATION_JSON)
                   .content(objectMapper.writeValueAsString(request)))
               .andDo(print())
               .andExpect(status().isCreated())
               .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
    }
}