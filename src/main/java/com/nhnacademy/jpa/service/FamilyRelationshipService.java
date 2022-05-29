package com.nhnacademy.jpa.service;

import com.nhnacademy.jpa.entity.Resident;
import com.nhnacademy.jpa.exception.FamilyRelationshipNotFoundException;
import com.nhnacademy.jpa.dto.request.familyrelationship.FamilyRelationshipDeleteRequest;
import com.nhnacademy.jpa.dto.request.familyrelationship.FamilyRelationshipRequest;
import com.nhnacademy.jpa.dto.response.familyrelationship.FamilyRelationshipResponse;
import com.nhnacademy.jpa.entity.FamilyRelationship;
import com.nhnacademy.jpa.exception.ResidentNotFound;
import com.nhnacademy.jpa.repository.FamilyRelationshipRepository;
import com.nhnacademy.jpa.repository.ResidentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class FamilyRelationshipService {

    private final FamilyRelationshipRepository familyRelationshipRepository;
    private final ResidentRepository residentRepository;

    @Transactional
    public FamilyRelationshipResponse insertFamilyRelationship(
        FamilyRelationshipRequest familyRelationshipRequest) {

        Resident resident =
            residentRepository.findById(familyRelationshipRequest.getSerialNumber())
                              .orElseThrow(ResidentNotFound::new);

        FamilyRelationship baseFamilyRelationship =
            new FamilyRelationship(familyRelationshipRequest, resident);

        FamilyRelationship saveFamilyRelationship =
            familyRelationshipRepository.save(baseFamilyRelationship);

        return new FamilyRelationshipResponse(saveFamilyRelationship);
    }

    @Transactional
    public FamilyRelationshipResponse updateFamilyRelationship(
        FamilyRelationshipRequest familyRelationshipRequest) {

        FamilyRelationship familyRelationship =
            familyRelationshipRepository.findById(new FamilyRelationship.FamilyRelationshipId(
                                            familyRelationshipRequest.getSerialNumber(),
                                            familyRelationshipRequest.getFamilySerialNumber()))
                                        .orElseThrow(FamilyRelationshipNotFoundException::new);

        familyRelationship.updateFamilyRelationshipCode(
            familyRelationshipRequest.getRelationship());

        return new FamilyRelationshipResponse(familyRelationship);
    }

    @Transactional
    public void deleteFamilyRelationship(
        FamilyRelationshipDeleteRequest familyRelationshipDeleteRequest) {

        familyRelationshipRepository.deleteById(new FamilyRelationship.FamilyRelationshipId(
            familyRelationshipDeleteRequest.getBaseSerialNumber(),
            familyRelationshipDeleteRequest.getResidentSerialNumber()));
    }
}
