package com.nhnacademy.jpa.service;

import static java.util.stream.Collectors.*;

import com.nhnacademy.jpa.dto.response.CertificationResponse;
import com.nhnacademy.jpa.dto.response.familyrelationship.FamilyRelationshipInfoResponse;
import com.nhnacademy.jpa.entity.CertificateIssue;
import com.nhnacademy.jpa.entity.Resident;
import com.nhnacademy.jpa.exception.CertificationNotFoundException;
import com.nhnacademy.jpa.exception.ResidentNotFound;
import com.nhnacademy.jpa.repository.CertificationRepository;
import com.nhnacademy.jpa.repository.FamilyRelationshipRepository;
import com.nhnacademy.jpa.repository.ResidentRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CertificationService {

    private static final String FAMILY_RELATIONSHIP = "가족관계증명서";
    private static final String RESIDENT_REPORT = "주민등록등본";

    private final CertificationRepository certificationRepository;
    private final FamilyRelationshipRepository familyRelationshipRepository;
    private final ResidentRepository residentRepository;


    public CertificationResponse getRelationshipCertificationInfo(Long residentSerialNumber) {
        Resident resident = residentRepository.findById(residentSerialNumber)
                                              .orElseThrow(ResidentNotFound::new);
        CertificateIssue certificateIssue =
            certificationRepository.findByResidentAndCertificateTypeCode(resident,
                                       FAMILY_RELATIONSHIP)
                                   .orElseThrow(CertificationNotFoundException::new);

        return new CertificationResponse(resident, certificateIssue);
    }

    public List<FamilyRelationshipInfoResponse> findFamilyRelationship(Long baseResidentSerialNumber) {

        Resident baseResident =
            residentRepository.findById(baseResidentSerialNumber)
                              .orElseThrow(ResidentNotFound::new);

        return familyRelationshipRepository.findFamilyRelationshipByBaseResident(baseResident.getId())
                                           .stream().map(FamilyRelationshipInfoResponse::new)
                                           .collect(toList());
    }
}
