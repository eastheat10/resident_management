package com.nhnacademy.jpa.dto.response.birthdeathreport;

import com.nhnacademy.jpa.entity.dto.BirthReportInfo;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import lombok.Getter;

@Getter
public class BirthReportInfoResponse {

    private final LocalDate reportedDate;
    private final String reportResidentQualification;
    private final String reportResidentPhoneNumber;
    private final String reportResidentEmail;
    private final String birthDate;
    private final String rrn;
    private final String name;
    private final String genderCode;
    private final String birthPlaceCode;
    private final String residentBaseAddress;
    private final String reportResidentName;
    private final String reportResidentRrn;

    public BirthReportInfoResponse(BirthReportInfo info) {
        this.reportedDate = info.getReportedDate();
        this.reportResidentQualification = info.getReportResidentQualification();
        this.reportResidentPhoneNumber = info.getReportResidentPhoneNumber();
        this.reportResidentEmail = info.getReportResidentEmail();
        this.birthDate = formatDateTime(info.getBirthDate());
        this.rrn = rrnMasking(info.getRrn());
        this.name = info.getName();
        this.genderCode = info.getGenderCode();
        this.birthPlaceCode = info.getBirthPlaceCode();
        this.residentBaseAddress = info.getResidentBaseAddress();
        this.reportResidentName = info.getReportResidentName();
        this.reportResidentRrn = rrnMasking(info.getReportResidentRrn());
    }

    private String rrnMasking(String rrn) {
        return rrn.substring(0, 7) + "*******";
    }

    private String formatDateTime(LocalDateTime birth) {
        return birth.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
    }

}
