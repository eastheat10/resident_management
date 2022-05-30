package com.nhnacademy.jpa.dto.response.certification;

import com.nhnacademy.jpa.entity.CertificateIssue;
import java.time.LocalDate;
import lombok.Getter;

@Getter
public class FamilyRelationshipInfoResponse {

    private final String certificateConfirmationNumber;
    private final String registrationBaseAddress;
    private final String certificateTypeCode;
    private final LocalDate date;
    private final String residentName;
    private final String residentRrn;
    private final LocalDate residentBirthDate;
    private final String residentGenderCode;

    public FamilyRelationshipInfoResponse(CertificateIssue issue) {
        this.certificateConfirmationNumber =
            getFormatCertificationNumber(issue.getCertificateConfirmationNumber());
        this.registrationBaseAddress = issue.getResident().getRegistrationBaseAddress();
        this.certificateTypeCode = issue.getCertificateTypeCode();
        this.date = issue.getDate();
        this.residentName = issue.getResident().getName();
        this.residentRrn = rrnMasking(issue.getResident().getRrn());
        this.residentBirthDate = issue.getResident().getBirthDate().toLocalDate();
        this.residentGenderCode = issue.getResident().getGenderCode();
    }

    private String rrnMasking(String rrn) {
        return rrn.substring(0, 7) + "*******";
    }

    private String getFormatCertificationNumber(Long number) {
        String n = String.valueOf(number);
        return n.substring(0, 8) + " - " + n.substring(8);
    }
}
