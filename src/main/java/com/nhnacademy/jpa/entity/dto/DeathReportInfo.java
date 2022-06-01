package com.nhnacademy.jpa.entity.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

public interface DeathReportInfo {

    LocalDate getReportedDate();

    String getReportResidentQualification();

    String getReportResidentPhoneNumber();

    String getReportResidentEmail();

    String getRrn();

    LocalDateTime getDeathDate();

    String getName();

    String getDeathPlaceCode();

    String getDeathPlaceAddress();

    String getReportResidentName();

    String getReportResidentRrn();
}
