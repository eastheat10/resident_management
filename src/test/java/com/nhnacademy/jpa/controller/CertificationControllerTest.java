package com.nhnacademy.jpa.controller;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.nhnacademy.jpa.dto.response.certification.CompositionResidentResponse;
import com.nhnacademy.jpa.dto.response.certification.FamilyRelationshipInfoResponse;
import com.nhnacademy.jpa.dto.response.certification.HouseholdRegistrationInfoResponse;
import com.nhnacademy.jpa.dto.response.familyrelationship.FamilyRelationshipResidentResponse;
import com.nhnacademy.jpa.dto.response.householdmovementaddress.HouseholdMovementAddressResponse;
import com.nhnacademy.jpa.service.CertificationService;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
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

        FamilyRelationshipInfoResponse
            response = mock(FamilyRelationshipInfoResponse.class);
        List<FamilyRelationshipResidentResponse> infoResponses = new ArrayList<>();

        when(service.getRelationshipCertificationInfo(anyLong())).thenReturn(response);
        when(service.findFamilyRelationship(anyLong())).thenReturn(infoResponses);

        MvcResult mvcResult =
            mockMvc.perform(get("/certification/family-relationship/{serialNumber}", "4"))
                   .andExpect(status().isOk())
                   .andExpect(view().name("certification/family-relationship"))
                   .andDo(print())
                   .andReturn();

        FamilyRelationshipInfoResponse response1 =
            (FamilyRelationshipInfoResponse) Objects.requireNonNull(mvcResult.getModelAndView())
                                                    .getModel()
                                                    .get("info");
        List<FamilyRelationshipResidentResponse> list =
            (List) Objects.requireNonNull(mvcResult.getModelAndView()).getModel().get("list");

        assertThat(response1).isEqualTo(response);
        assertThat(list).isEqualTo(infoResponses);
    }

    @Test
    @DisplayName("주민등록등본")
    void householdRegistration() throws Exception {

//        HouseholdRegistrationInfoResponse resp = mock(HouseholdRegistrationInfoResponse.class);
//        List<HouseholdMovementAddressResponse> movement = new ArrayList<>();
//        List<CompositionResidentResponse> compositions = spy(new ArrayList<>());
//
//        when(service.getHouseholdRegistrationResponse(anyLong())).thenReturn(resp);
//        when(service.getMovementList(anyLong())).thenReturn(movement);
//        when(service.getHouseholdComposition(anyLong())).thenReturn(compositions);
//
//        MvcResult mvcResult =
//            mockMvc.perform(get("/certification/household-registration/{serialNumber}", "4"))
//                   .andExpect(status().isOk())
//                   .andExpect(view().name("certification/household-registration"))
//                   .andDo(print())
//                   .andReturn();
//
//        HouseholdRegistrationInfoResponse info =
//            (HouseholdRegistrationInfoResponse) mvcResult.getModelAndView().getModel().get("info");
//        HouseholdRegistrationInfoResponse p =
//            (HouseholdRegistrationInfoResponse) mvcResult.getModelAndView().getModel()
//                                                         .get("person");
//
//        List<HouseholdMovementAddressResponse> movementList =
//            (List<HouseholdMovementAddressResponse>) mvcResult.getModelAndView().getModel()
//                                                              .get("movementList");
//        List<CompositionResidentResponse> composition =
//            (List<CompositionResidentResponse>) mvcResult.getModelAndView().getModel()
//                                                         .get("compositions");
//
//        assertThat(info).isEqualTo(resp);
//        assertThat(movementList).isEqualTo(movement);
//        assertThat(compositions).isEqualTo(composition);
//
//        assertThat(mvcResult.getModelAndView().getModel().size()).isEqualTo(4);
    }
}