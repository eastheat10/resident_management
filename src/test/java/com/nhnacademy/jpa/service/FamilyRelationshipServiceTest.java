package com.nhnacademy.jpa.service;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.nhnacademy.jpa.dto.request.familyrelationship.FamilyRelationshipDeleteRequest;
import com.nhnacademy.jpa.dto.request.familyrelationship.FamilyRelationshipRequest;
import com.nhnacademy.jpa.dto.response.familyrelationship.FamilyRelationshipResponse;
import com.nhnacademy.jpa.entity.FamilyRelationship;
import com.nhnacademy.jpa.entity.Resident;
import com.nhnacademy.jpa.repository.FamilyRelationshipRepository;
import com.nhnacademy.jpa.repository.ResidentRepository;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class FamilyRelationshipServiceTest {

    FamilyRelationshipRepository familyRelationshipRepository;
    ResidentRepository residentRepository;
    FamilyRelationshipService familyRelationshipService;

    @BeforeEach
    void setUp() {
        familyRelationshipRepository = mock(FamilyRelationshipRepository.class);
        residentRepository = mock(ResidentRepository.class);
        familyRelationshipService =
            new FamilyRelationshipService(familyRelationshipRepository, residentRepository);
    }

    @Test
    @DisplayName("가족관계 등록")
    void insert() {
        FamilyRelationshipRequest dto = mock(FamilyRelationshipRequest.class);
        FamilyRelationship familyRelationship = mock(FamilyRelationship.class);
        FamilyRelationship.FamilyRelationshipId id =
            mock(FamilyRelationship.FamilyRelationshipId.class);

        Resident resident = mock(Resident.class);

        when(familyRelationship.getFamilyRelationshipId()).thenReturn(id);

        when(id.getBaseResidentSerialNumber()).thenReturn(1L);
        when(id.getFamilyResidentSerialNumber()).thenReturn(1L);

        when(familyRelationship.getFamilyRelationshipCode()).thenReturn("");

        when(dto.getSerialNumber()).thenReturn(1L);
        when(residentRepository.findById(dto.getSerialNumber())).thenReturn(Optional.of(resident));
        when(familyRelationshipRepository.save(any())).thenReturn(familyRelationship);

        FamilyRelationshipResponse response =
            familyRelationshipService.insertFamilyRelationship(dto);

        assertThat(response).isNotNull();
    }

    @Test
    @DisplayName("가족관계 수정")
    void update() {
        FamilyRelationshipRequest dto = mock(FamilyRelationshipRequest.class);
        FamilyRelationship familyRelationship = mock(FamilyRelationship.class);
        FamilyRelationship.FamilyRelationshipId id =
            mock(FamilyRelationship.FamilyRelationshipId.class);

        when(familyRelationship.getFamilyRelationshipId()).thenReturn(id);

        when(id.getBaseResidentSerialNumber()).thenReturn(0L);
        when(id.getFamilyResidentSerialNumber()).thenReturn(0L);
        when(familyRelationship.getFamilyRelationshipCode()).thenReturn("");

        when(dto.getSerialNumber()).thenReturn(0L);
        when(dto.getFamilySerialNumber()).thenReturn(0L);
        when(dto.getRelationship()).thenReturn("");

        when(familyRelationshipRepository.findById(any())).thenReturn(
            Optional.of(familyRelationship));
        doNothing().when(familyRelationship).updateFamilyRelationshipCode(anyString());

        FamilyRelationshipResponse response =
            familyRelationshipService.updateFamilyRelationship(dto);

        assertThat(response).isNotNull();
    }

    @Test
    @DisplayName("가족관계 삭제")
    void delete() {
        FamilyRelationshipDeleteRequest dto = mock(FamilyRelationshipDeleteRequest.class);
        FamilyRelationship.FamilyRelationshipId id =
            mock(FamilyRelationship.FamilyRelationshipId.class);

        when(dto.getBaseSerialNumber()).thenReturn(0L);
        when(dto.getResidentSerialNumber()).thenReturn(0L);

        doNothing().when(familyRelationshipRepository)
                   .deleteById(any(FamilyRelationship.FamilyRelationshipId.class));

        familyRelationshipService.deleteFamilyRelationship(dto);

        verify(familyRelationshipRepository, times(1)).deleteById(
            any(FamilyRelationship.FamilyRelationshipId.class));
    }
}