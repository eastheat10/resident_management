package com.nhnacademy.jpa.service;

import com.nhnacademy.jpa.dto.request.birthdeathreport.BirthDeathReportRequest;
import com.nhnacademy.jpa.dto.response.birthdeathreport.BirthReportResponse;
import com.nhnacademy.jpa.dto.response.birthdeathreport.DeathReportResponse;
import com.nhnacademy.jpa.entity.BirthDeathReportResident;
import com.nhnacademy.jpa.entity.Resident;
import com.nhnacademy.jpa.exception.ReportNotFoundException;
import com.nhnacademy.jpa.exception.ResidentNotFound;
import com.nhnacademy.jpa.repository.BirthDeathReportRepository;
import com.nhnacademy.jpa.repository.ResidentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class BirthDeathReportService {

    public static final String BIRTH = "출생";
    public static final String DEATH = "사망";

    private final BirthDeathReportRepository birthDeathReportRepository;
    private final ResidentRepository residentRepository;

    @Transactional
    public BirthReportResponse birthReportInsert(BirthDeathReportRequest insertRequest) {

        Resident resident =
            residentRepository.findById(insertRequest.getResidentSerialNumber())
                              .orElseThrow(ResidentNotFound::new);
        Resident reportResident =
            residentRepository.findById(insertRequest.getReportResidentSerialNumber())
                              .orElseThrow(ResidentNotFound::new);

        BirthDeathReportResident birthDeathReport =
            new BirthDeathReportResident(insertRequest, resident, reportResident);

        BirthDeathReportResident saveBirthDeathReport =
            birthDeathReportRepository.save(birthDeathReport);

        return new BirthReportResponse(saveBirthDeathReport);
    }

    @Transactional
    public BirthReportResponse birthModify(BirthDeathReportRequest request) {

        BirthDeathReportResident.BirthDeathReportResidentId id =
            new BirthDeathReportResident.BirthDeathReportResidentId(BIRTH,
                request.getReportResidentSerialNumber(), request.getResidentSerialNumber());

        BirthDeathReportResident birthDeathReportResident =
            birthDeathReportRepository.findById(id).orElseThrow(ReportNotFoundException::new);

        birthDeathReportResident.modify(request);

        return new BirthReportResponse(birthDeathReportResident);
    }

    @Transactional
    public void birthDelete(BirthDeathReportRequest deleteRequest) {

        BirthDeathReportResident.BirthDeathReportResidentId id =
            new BirthDeathReportResident.BirthDeathReportResidentId(
                BIRTH,
                deleteRequest.getReportResidentSerialNumber(),
                deleteRequest.getResidentSerialNumber());

        birthDeathReportRepository.deleteById(id);
    }

    @Transactional
    public DeathReportResponse deathReportInsert(BirthDeathReportRequest insertRequest) {

        Resident resident =
            residentRepository.findById(insertRequest.getResidentSerialNumber())
                              .orElseThrow(ResidentNotFound::new);
        Resident reportResident =
            residentRepository.findById(insertRequest.getReportResidentSerialNumber())
                              .orElseThrow(ResidentNotFound::new);

        BirthDeathReportResident birthDeathReport =
            new BirthDeathReportResident(insertRequest, resident, reportResident);

        BirthDeathReportResident saveBirthDeathReport =
            birthDeathReportRepository.save(birthDeathReport);

        return new DeathReportResponse(saveBirthDeathReport);
    }

    @Transactional
    public DeathReportResponse deathReportModify(BirthDeathReportRequest modifyRequest) {

        BirthDeathReportResident.BirthDeathReportResidentId id =
            new BirthDeathReportResident.BirthDeathReportResidentId(DEATH,
                modifyRequest.getReportResidentSerialNumber(), modifyRequest.getResidentSerialNumber());

        BirthDeathReportResident birthDeathReportResident =
            birthDeathReportRepository.findById(id).orElseThrow(ReportNotFoundException::new);

        birthDeathReportResident.modify(modifyRequest);

        return new DeathReportResponse(birthDeathReportResident);
    }

    @Transactional
    public void deathDelete(BirthDeathReportRequest deleteRequest) {

        BirthDeathReportResident.BirthDeathReportResidentId id =
            new BirthDeathReportResident.BirthDeathReportResidentId(
                DEATH,
                deleteRequest.getReportResidentSerialNumber(),
                deleteRequest.getResidentSerialNumber());

        birthDeathReportRepository.deleteById(id);
    }
}
