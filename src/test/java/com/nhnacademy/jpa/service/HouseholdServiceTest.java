package com.nhnacademy.jpa.service;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.nhnacademy.jpa.dto.request.household.HouseholdRequest;
import com.nhnacademy.jpa.dto.response.household.HouseholdResponse;
import com.nhnacademy.jpa.entity.Household;
import com.nhnacademy.jpa.entity.Resident;
import com.nhnacademy.jpa.repository.HouseholdRepository;
import com.nhnacademy.jpa.repository.ResidentRepository;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class HouseholdServiceTest {

    HouseholdService service;
    HouseholdRepository householdRepository;
    ResidentRepository residentRepository;

    @BeforeEach
    void setUp() {
        householdRepository = mock(HouseholdRepository.class);
        residentRepository = mock(ResidentRepository.class);
        service = new HouseholdService(householdRepository, residentRepository);
    }

    @Test
    @DisplayName("세대 추가")
    void insert() {

        HouseholdRequest request = new HouseholdRequest();
        Household household = spy(new Household());
        Resident resident = spy(new Resident());

        when(residentRepository.findById(anyLong())).thenReturn(Optional.of(resident));
        when(householdRepository.save(any(Household.class))).thenReturn(household);

        when(household.getResident()).thenReturn(resident);
        when(resident.getId()).thenReturn(0L);

        HouseholdResponse insert = service.insert(request);

        assertThat(insert).isNotNull();
    }

    @Test
    @DisplayName("세대 삭제")
    void delete() {

        service.delete(anyLong());

        verify(householdRepository, times(1)).deleteById(anyLong());
    }
}