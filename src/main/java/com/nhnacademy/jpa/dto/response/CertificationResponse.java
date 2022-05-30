package com.nhnacademy.jpa.dto.response;

import com.nhnacademy.jpa.entity.CertificateIssue;
import com.nhnacademy.jpa.entity.Resident;
import java.time.LocalDate;
import lombok.Getter;

@Getter
public class CertificationResponse {

    private final String certificateConfirmationNumber;
    private final String registrationBaseAddress;
    private final String certificateTypeCode;
    private final LocalDate date;
    private final String residentName;
    private final String residentRrn;
    private final LocalDate residentBirthDate;
    private final String residentGenderCode;

    public CertificationResponse(Resident resident, CertificateIssue issue) {
        this.certificateConfirmationNumber =
            getFormatCertificationNumber(issue.getCertificateConfirmationNumber());
        this.registrationBaseAddress = resident.getRegistrationBaseAddress();
        this.certificateTypeCode = issue.getCertificateTypeCode();
        this.date = issue.getDate();
        this.residentName = resident.getName();
        this.residentRrn = rrnMasking(resident.getRrn());
        this.residentBirthDate = resident.getBirthDate().toLocalDate();
        this.residentGenderCode = resident.getGenderCode();
    }

    private String rrnMasking(String rrn) {
        return rrn.substring(0, 7) + "*******";
    }

    private String getFormatCertificationNumber(Long number) {
        String n = String.valueOf(number);
        return n.substring(0, 8) + " - " + n.substring(8);
    }
}
