package com.nhnacademy.jpa.service;

import com.nhnacademy.jpa.dto.request.household.HouseholdRequest;
import com.nhnacademy.jpa.dto.request.householdmovementaddress.HouseholdMovementAddressRequest;
import com.nhnacademy.jpa.dto.response.household.HouseholdResponse;
import com.nhnacademy.jpa.dto.response.householdmovementaddress.HouseholdMovementAddressResponse;
import com.nhnacademy.jpa.entity.Household;
import com.nhnacademy.jpa.entity.HouseholdMovementAddress;
import com.nhnacademy.jpa.entity.Resident;
import com.nhnacademy.jpa.exception.HouseholdMovementAddressNotFoundException;
import com.nhnacademy.jpa.exception.HouseholdNotFoundException;
import com.nhnacademy.jpa.exception.ResidentNotFound;
import com.nhnacademy.jpa.repository.HouseholdMovementAddressRepository;
import com.nhnacademy.jpa.repository.HouseholdRepository;
import com.nhnacademy.jpa.repository.ResidentRepository;
import java.time.LocalDate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class HouseholdService {

    private final HouseholdRepository householdRepository;
    private final ResidentRepository residentRepository;
    private final HouseholdMovementAddressRepository householdMovementAddressRepository;

    @Transactional
    public HouseholdResponse insertHousehold(HouseholdRequest request) {

        Resident resident = residentRepository.findById(request.getHouseholdResidentNumber())
                                              .orElseThrow(ResidentNotFound::new);
        Household household = new Household(request, resident);

        Household savedHousehold = householdRepository.save(household);

        return new HouseholdResponse(savedHousehold);
    }

    @Transactional
    public void deleteHousehold(Long id) {
        householdRepository.deleteById(id);
    }

    @Transactional
    public HouseholdMovementAddressResponse insertMovementAddress(
        HouseholdMovementAddressRequest request, Long householdSerialNumber) {

        Household household =
            householdRepository.findById(householdSerialNumber)
                               .orElseThrow(HouseholdNotFoundException::new);

        HouseholdMovementAddress householdMovementAddress =
            new HouseholdMovementAddress(request, household);

        HouseholdMovementAddress lastMovement =
            householdMovementAddressRepository.findByLastAddressYN("Y")
                                              .orElseThrow(
                                                  HouseholdMovementAddressNotFoundException::new);

        lastMovement.makeOldAddress();

        HouseholdMovementAddress savedHouseholdMovementAddress =
            householdMovementAddressRepository.save(householdMovementAddress);

        return new HouseholdMovementAddressResponse(savedHouseholdMovementAddress);
    }

    @Transactional
    public HouseholdMovementAddressResponse updateMovementAddress(
        HouseholdMovementAddressRequest request, Long householdSerialNumber, LocalDate reportDate) {

        HouseholdMovementAddress.HouseholdMovementAddressId id =
            new HouseholdMovementAddress.HouseholdMovementAddressId(reportDate,
                householdSerialNumber);

        HouseholdMovementAddress householdMovementAddress =
            householdMovementAddressRepository.findById(id)
                                              .orElseThrow(
                                                  HouseholdMovementAddressNotFoundException::new);

        householdMovementAddress.modify(request);

        return new HouseholdMovementAddressResponse(householdMovementAddress);
    }

    @Transactional
    public void deleteMovementAddress(Long houseSerialNumber, LocalDate reportDate) {

        HouseholdMovementAddress.HouseholdMovementAddressId id =
            new HouseholdMovementAddress.HouseholdMovementAddressId(reportDate, houseSerialNumber);

        householdMovementAddressRepository.deleteById(id);
    }
}
