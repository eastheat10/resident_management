package com.nhnacademy.jpa.entity.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

public interface BirthReportInfo {

    LocalDate getReportedDate();

    String getReportResidentQualification();

    String getReportResidentPhoneNumber();

    String getReportResidentEmail();

    String getRrn();

    LocalDateTime getBirthDate();

    String getName();

    String getGenderCode();

    String getBirthPlaceCode();

    String getResidentBaseAddress();

    String getReportResidentName();

    String getReportResidentRrn();
}
