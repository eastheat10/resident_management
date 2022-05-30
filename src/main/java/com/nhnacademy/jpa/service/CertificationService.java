package com.nhnacademy.jpa.service;

import static java.util.stream.Collectors.*;

import com.nhnacademy.jpa.dto.response.certification.CompositionResidentResponse;
import com.nhnacademy.jpa.dto.response.certification.FamilyRelationshipInfoResponse;
import com.nhnacademy.jpa.dto.response.certification.HouseholdRegistrationInfoResponse;
import com.nhnacademy.jpa.dto.response.familyrelationship.FamilyRelationshipResidentResponse;
import com.nhnacademy.jpa.dto.response.householdmovementaddress.HouseholdMovementAddressResponse;
import com.nhnacademy.jpa.entity.CertificateIssue;
import com.nhnacademy.jpa.entity.Household;
import com.nhnacademy.jpa.entity.HouseholdMovementAddress;
import com.nhnacademy.jpa.entity.Resident;
import com.nhnacademy.jpa.exception.HouseholdNotFoundException;
import com.nhnacademy.jpa.exception.ResidentNotFound;
import com.nhnacademy.jpa.repository.CertificationRepository;
import com.nhnacademy.jpa.repository.FamilyRelationshipRepository;
import com.nhnacademy.jpa.repository.HouseholdCompositionResidentRepository;
import com.nhnacademy.jpa.repository.HouseholdMovementAddressRepository;
import com.nhnacademy.jpa.repository.HouseholdRepository;
import com.nhnacademy.jpa.repository.ResidentRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CertificationService {

    private static final String FAMILY_RELATIONSHIP = "가족관계증명서";
    private static final String HOUSEHOLD_REGISTRATION = "주민등록등본";

    private final CertificationRepository certificationRepository;
    private final FamilyRelationshipRepository familyRelationshipRepository;
    private final ResidentRepository residentRepository;
    private final HouseholdRepository householdRepository;
    private final HouseholdMovementAddressRepository movementAddressRepository;
    private final HouseholdCompositionResidentRepository compositionRepository;


    public FamilyRelationshipInfoResponse getRelationshipCertificationInfo(
        Long residentSerialNumber) {

        Resident resident = residentRepository.findById(residentSerialNumber)
                                              .orElseThrow(ResidentNotFound::new);
        CertificateIssue certificateIssue =
            certificationRepository
                .findByResidentAndCertificateTypeCodeOrderByDateDesc(resident, FAMILY_RELATIONSHIP)
                .get(0);

        return new FamilyRelationshipInfoResponse(certificateIssue);
    }

    public List<FamilyRelationshipResidentResponse> findFamilyRelationship(
        Long baseResidentSerialNumber) {

        Resident baseResident =
            residentRepository.findById(baseResidentSerialNumber)
                              .orElseThrow(ResidentNotFound::new);

        return familyRelationshipRepository.findFamilyRelationshipByBaseResident(
                                               baseResident.getId())
                                           .stream().map(
                FamilyRelationshipResidentResponse::new)
                                           .collect(toList());
    }

    public HouseholdRegistrationInfoResponse getHouseholdRegistrationResponse(
        Long residentSerialNumber) {

        Resident resident = residentRepository.findById(residentSerialNumber)
                                              .orElseThrow(ResidentNotFound::new);
        Household household = householdRepository.findByResident(resident)
                                                 .orElseThrow(HouseholdNotFoundException::new);

        CertificateIssue certificateIssue =
            certificationRepository
                .findByResidentAndCertificateTypeCodeOrderByDateDesc(resident,
                    HOUSEHOLD_REGISTRATION)
                .get(0);

        return new HouseholdRegistrationInfoResponse(household, resident, certificateIssue);
    }

    public List<HouseholdMovementAddressResponse> getMovementList(Long residentSerialNumber) {
        Resident resident = residentRepository.findById(residentSerialNumber)
                                              .orElseThrow(ResidentNotFound::new);
        Household household = householdRepository.findByResident(resident)
                                                 .orElseThrow(HouseholdNotFoundException::new);

        List<HouseholdMovementAddress> movementAddressList =
            movementAddressRepository.findByHousehold(household);

        return movementAddressList.stream()
                                  .map(HouseholdMovementAddressResponse::new)
                                  .collect(toList());
    }

    public List<CompositionResidentResponse> getHouseholdComposition(Long residentSerialNumber) {
        Resident resident = residentRepository.findById(residentSerialNumber)
                                              .orElseThrow(ResidentNotFound::new);
        Household household = householdRepository.findByResident(resident)
                                                 .orElseThrow(HouseholdNotFoundException::new);

        return compositionRepository.findByHousehold(household)
                                    .stream()
                                    .map(CompositionResidentResponse::new)
                                    .collect(toList());
    }
}
