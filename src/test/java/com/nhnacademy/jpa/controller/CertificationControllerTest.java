package com.nhnacademy.jpa.controller;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.nhnacademy.jpa.dto.response.CertificationResponse;
import com.nhnacademy.jpa.dto.response.familyrelationship.FamilyRelationshipInfoResponse;
import com.nhnacademy.jpa.service.CertificationService;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

class CertificationControllerTest {

    CertificationService service;
    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        service = mock(CertificationService.class);
        mockMvc = MockMvcBuilders.standaloneSetup(new CertificationController(service))
                                 .build();
    }

    @Test
    @DisplayName("가족관계증명서")
    void familyRelationshipCertification() throws Exception {

        CertificationResponse response = mock(CertificationResponse.class);
        List<FamilyRelationshipInfoResponse> infoResponses = new ArrayList<>();

        when(service.getRelationshipCertificationInfo(anyLong())).thenReturn(response);
        when(service.findFamilyRelationship(anyLong())).thenReturn(infoResponses);

        MvcResult mvcResult =
            mockMvc.perform(get("/certification/family-relationship/{serialNumber}", "4"))
                   .andExpect(status().isOk())
                   .andExpect(view().name("certification/family-relationship"))
                   .andDo(print())
                   .andReturn();

        CertificationResponse response1 =
            (CertificationResponse) Objects.requireNonNull(mvcResult.getModelAndView()).getModel()
                                           .get("info");
        List<FamilyRelationshipInfoResponse> list =
            (List) Objects.requireNonNull(mvcResult.getModelAndView()).getModel().get("list");

        assertThat(response1).isEqualTo(response);
        assertThat(list).isEqualTo(infoResponses);
    }
}