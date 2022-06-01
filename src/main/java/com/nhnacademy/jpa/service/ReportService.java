package com.nhnacademy.jpa.service;

import static java.util.stream.Collectors.*;

import com.nhnacademy.jpa.dto.response.birthdeathreport.BirthReportInfoResponse;
import com.nhnacademy.jpa.dto.response.birthdeathreport.ParentInfoResponse;
import com.nhnacademy.jpa.entity.dto.BirthReportInfo;
import com.nhnacademy.jpa.exception.BirthReportNotFoundException;
import com.nhnacademy.jpa.repository.BirthDeathReportRepository;
import com.nhnacademy.jpa.repository.FamilyRelationshipRepository;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReportService {

    private final BirthDeathReportRepository reportRepository;
    private final FamilyRelationshipRepository relationshipRepository;

    public BirthReportInfoResponse birthReportInfo(Long id) {

        BirthReportInfo info = Optional.ofNullable(reportRepository.findBirthInfoByResidentId(id))
                                        .orElseThrow(BirthReportNotFoundException::new);
        return new BirthReportInfoResponse(info);
    }

    public List<ParentInfoResponse> parentInfo(Long id) {

        return relationshipRepository.findParent(id)
                                     .stream()
                                     .map(ParentInfoResponse::new)
                                     .collect(toList());
    }
}
