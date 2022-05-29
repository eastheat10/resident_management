package com.nhnacademy.jpa.dto.request.birthdeathreport;

import java.time.LocalDate;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class BirthReportInsertRequest {

    private Long reportResidentSerialNumber;
    private LocalDate birthDeathReportDate;
    private String birthReportQualificationsCode;
    private String emailAddress;
    private String phoneNumber;
}
