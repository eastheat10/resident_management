package com.nhnacademy.jpa.dto.request.birthdeathreport;

import java.time.LocalDate;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class DeathReportInsertRequest {

    private Long residentSerialNumber;
    private LocalDate birthDeathReportDate;
    private String deathReportQualificationsCode;
    private String emailAddress;
    private String phoneNumber;
}
