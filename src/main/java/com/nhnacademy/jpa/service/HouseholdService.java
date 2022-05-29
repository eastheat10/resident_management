package com.nhnacademy.jpa.service;

import com.nhnacademy.jpa.dto.request.household.HouseholdRequest;
import com.nhnacademy.jpa.dto.request.resident.ResidentRequest;
import com.nhnacademy.jpa.dto.response.household.HouseholdResponse;
import com.nhnacademy.jpa.entity.Household;
import com.nhnacademy.jpa.entity.Resident;
import com.nhnacademy.jpa.exception.ResidentNotFound;
import com.nhnacademy.jpa.repository.HouseholdRepository;
import com.nhnacademy.jpa.repository.ResidentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class HouseholdService {

    private final HouseholdRepository householdRepository;
    private final ResidentRepository residentRepository;

    @Transactional
    public HouseholdResponse insert(HouseholdRequest request) {

        Resident resident = residentRepository.findById(request.getHouseholdResidentNumber())
                                              .orElseThrow(ResidentNotFound::new);
        Household household = new Household(request, resident);

        Household savedHousehold = householdRepository.save(household);

        return new HouseholdResponse(savedHousehold);
    }

    public void delete(Long id) {
        householdRepository.deleteById(id);
    }
}
