package com.nhnacademy.jpa.dto.request.birthdeathreport;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class BirthDeathReportRequest {

    private String birthDeathTypeCode;
    private Long reportResidentSerialNumber;
    private Long residentSerialNumber;
    private LocalDate birthDeathReportDate;
    private String birthReportQualificationsCode;
    private String deathReportQualificationsCode;
    private String emailAddress;
    private String phoneNumber;
}
