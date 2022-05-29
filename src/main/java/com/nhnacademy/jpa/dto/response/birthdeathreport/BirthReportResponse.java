package com.nhnacademy.jpa.dto.response.birthdeathreport;

import com.nhnacademy.jpa.entity.BirthDeathReportResident;
import java.time.LocalDate;
import lombok.Getter;

@Getter
public class BirthReportResponse {

    private final String birthDeathTypeCode;
    private final Long reportResidentSerialNumber;
    private final Long residentSerialNumber;
    private final LocalDate birthDeathReportDate;
    private final String birthReportQualificationsCode;
    private final String emailAddress;
    private final String phoneNumber;

    public BirthReportResponse(BirthDeathReportResident report) {
        this.birthDeathTypeCode = report.getBirthDeathReportResidentId().getBirthDeathTypeCode();
        this.reportResidentSerialNumber = report.getReportResident().getId();
        this.residentSerialNumber = report.getResident().getId();
        this.birthDeathReportDate = report.getBirthDeathReportDate();
        this.birthReportQualificationsCode = report.getBirthReportQualificationsCode();
        this.emailAddress = report.getEmailAddress();
        this.phoneNumber = report.getPhoneNumber();
    }
}
