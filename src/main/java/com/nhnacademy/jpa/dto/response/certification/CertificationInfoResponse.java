package com.nhnacademy.jpa.dto.response.certification;

import com.nhnacademy.jpa.entity.CertificateIssue;
import java.time.LocalDate;
import lombok.Getter;

@Getter
public class CertificationInfoResponse {

    private final String certificationNumber;
    private final String certificationTypeCode;
    private final LocalDate issueDate;

    public CertificationInfoResponse(CertificateIssue certification) {
        this.certificationNumber =
            getFormatCertificationNumber(certification.getCertificateConfirmationNumber());
        this.certificationTypeCode = certification.getCertificateTypeCode();
        this.issueDate = certification.getDate();
    }

    private String getFormatCertificationNumber(Long number) {
        String n = String.valueOf(number);
        return n.substring(0, 8) + " - " + n.substring(8);
    }
}
