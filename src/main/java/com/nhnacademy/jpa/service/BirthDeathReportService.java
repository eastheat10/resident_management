package com.nhnacademy.jpa.service;

import com.nhnacademy.jpa.dto.request.birthdeathreport.BirthDeathReportRequest;
import com.nhnacademy.jpa.dto.response.birthdeathreport.BirthReportResponse;
import com.nhnacademy.jpa.entity.BirthDeathReportResident;
import com.nhnacademy.jpa.entity.Resident;
import com.nhnacademy.jpa.exception.ReportNotFoundException;
import com.nhnacademy.jpa.repository.BirthDeathReportRepository;
import com.nhnacademy.jpa.repository.ResidentRepository;
import javax.persistence.NoResultException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class BirthDeathReportService {

    private final BirthDeathReportRepository birthDeathReportRepository;
    private final ResidentRepository residentRepository;

    @Transactional
    public BirthReportResponse birthReportInsert(BirthDeathReportRequest insertRequest) {

        Resident resident =
            residentRepository.findById(insertRequest.getResidentSerialNumber())
                              .orElseThrow(NoResultException::new);
        Resident reportResident =
            residentRepository.findById(insertRequest.getReportResidentSerialNumber())
                              .orElseThrow(NoResultException::new);

        BirthDeathReportResident birthDeathReport =
            new BirthDeathReportResident(insertRequest, resident, reportResident);

        BirthDeathReportResident saveBirthDeathReport =
            birthDeathReportRepository.save(birthDeathReport);

        return new BirthReportResponse(saveBirthDeathReport);
    }

    public BirthReportResponse birthModify(BirthDeathReportRequest request) {
        BirthDeathReportResident.BirthDeathReportResidentId id =
            new BirthDeathReportResident.BirthDeathReportResidentId("출생",
                request.getReportResidentSerialNumber(), request.getResidentSerialNumber());

        BirthDeathReportResident birthDeathReportResident =
            birthDeathReportRepository.findById(id).orElseThrow(ReportNotFoundException::new);

        birthDeathReportResident.modify(request);

        return new BirthReportResponse(birthDeathReportResident);
    }

    public void birthDelete(BirthDeathReportRequest deleteRequest) {
        BirthDeathReportResident.BirthDeathReportResidentId id =
            new BirthDeathReportResident.BirthDeathReportResidentId(
                deleteRequest.getBirthDeathTypeCode(),
                deleteRequest.getReportResidentSerialNumber(),
                deleteRequest.getResidentSerialNumber());

        birthDeathReportRepository.deleteById(id);
    }
}
