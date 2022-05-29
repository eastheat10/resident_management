package com.nhnacademy.jpa.service;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.nhnacademy.jpa.dto.request.household.HouseholdRequest;
import com.nhnacademy.jpa.dto.request.householdmovementaddress.HouseholdMovementAddressRequest;
import com.nhnacademy.jpa.dto.response.household.HouseholdResponse;
import com.nhnacademy.jpa.dto.response.householdmovementaddress.HouseholdMovementAddressResponse;
import com.nhnacademy.jpa.entity.Household;
import com.nhnacademy.jpa.entity.HouseholdMovementAddress;
import com.nhnacademy.jpa.entity.Resident;
import com.nhnacademy.jpa.repository.HouseholdMovementAddressRepository;
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
    HouseholdMovementAddressRepository movementAddressRepository;

    @BeforeEach
    void setUp() {
        householdRepository = mock(HouseholdRepository.class);
        residentRepository = mock(ResidentRepository.class);
        movementAddressRepository = mock(HouseholdMovementAddressRepository.class);
        service = new HouseholdService(householdRepository, residentRepository,
            movementAddressRepository);
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

        HouseholdResponse insert = service.insertHousehold(request);

        assertThat(insert).isNotNull();
    }

    @Test
    @DisplayName("세대 삭제")
    void delete() {

        service.deleteHousehold(anyLong());

        verify(householdRepository, times(1)).deleteById(anyLong());
    }

    @Test
    @DisplayName("주소이전추가")
    void movementInsert() {

        HouseholdMovementAddressRequest request = new HouseholdMovementAddressRequest();
        HouseholdMovementAddress.HouseholdMovementAddressId id =
            spy(new HouseholdMovementAddress.HouseholdMovementAddressId());
        Household household = new Household();
        HouseholdMovementAddress movementAddress = spy(new HouseholdMovementAddress());
        HouseholdMovementAddress lastMovement = mock(HouseholdMovementAddress.class);

        when(householdRepository.findById(anyLong())).thenReturn(Optional.of(household));

        when(movementAddress.getHouseholdMovementAddressId()).thenReturn(id);
        when(id.getHouseMovementReportDate()).thenReturn(null);

        doNothing().when(lastMovement).makeOldAddress();

        when(movementAddressRepository.findByLastAddressYN("Y")).thenReturn(Optional.of(lastMovement));
        when(movementAddressRepository.save(any(HouseholdMovementAddress.class)))
            .thenReturn(movementAddress);

        HouseholdMovementAddressResponse householdMovementAddressResponse =
            service.insertMovementAddress(request, 0L);

        assertThat(householdMovementAddressResponse).isNotNull();
        verify(lastMovement, times(1)).makeOldAddress();
    }

    @Test
    @DisplayName("주소이전 수정")
    void movementModify() {

        HouseholdMovementAddressRequest request = new HouseholdMovementAddressRequest();
        HouseholdMovementAddress movementAddress = spy(new HouseholdMovementAddress());
        HouseholdMovementAddress.HouseholdMovementAddressId id =
            new HouseholdMovementAddress.HouseholdMovementAddressId();

        when(movementAddress.getHouseholdMovementAddressId()).thenReturn(id);
        when(movementAddressRepository.findById(any())).thenReturn(Optional.of(movementAddress));

        HouseholdMovementAddressResponse response =
            service.updateMovementAddress(request, 0L, null);

        verify(movementAddress, times(1)).modify(request);
        assertThat(response).isNotNull();
    }

    @Test
    @DisplayName("주소이전정보 삭제")
    void deleteMovement() {

        service.deleteMovementAddress(0L, null);
        doNothing().when(movementAddressRepository).deleteById(any());
        verify(movementAddressRepository, times(1)).deleteById(any());
    }
}