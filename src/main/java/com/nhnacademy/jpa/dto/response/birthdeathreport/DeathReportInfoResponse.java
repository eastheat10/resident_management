package com.nhnacademy.jpa.dto.response.birthdeathreport;

import com.nhnacademy.jpa.entity.dto.DeathReportInfo;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import lombok.Getter;

@Getter
public class DeathReportInfoResponse {

    private final LocalDate reportedDate;
    private final String reportResidentQualification;
    private final String reportResidentPhoneNumber;
    private final String reportResidentEmail;
    private final String rrn;
    private final String deathDate;
    private final String name;
    private final String deathPlaceCode;
    private final String deathPlaceAddress;
    private final String reportResidentName;
    private final String reportResidentRrn;

    public DeathReportInfoResponse(DeathReportInfo report) {
        this.reportedDate = report.getReportedDate();
        this.reportResidentQualification = report.getReportResidentQualification();
        this.reportResidentPhoneNumber = report.getReportResidentPhoneNumber();
        this.reportResidentEmail = report.getReportResidentEmail();
        this.rrn = rrnMasking(report.getRrn());
        this.deathDate = formatDateTime(report.getDeathDate());
        this.name = report.getName();
        this.deathPlaceCode = report.getDeathPlaceCode();
        this.deathPlaceAddress = report.getDeathPlaceAddress();
        this.reportResidentName = report.getReportResidentName();
        this.reportResidentRrn = rrnMasking(report.getReportResidentRrn());
    }

    private String rrnMasking(String rrn) {
        return rrn.substring(0, 7) + "*******";
    }

    private String formatDateTime(LocalDateTime birth) {
        return birth.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
    }
}
