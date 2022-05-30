package com.nhnacademy.jpa.dto.response.certification;

import com.nhnacademy.jpa.entity.CertificateIssue;
import com.nhnacademy.jpa.entity.Household;
import com.nhnacademy.jpa.entity.Resident;
import java.time.LocalDate;
import lombok.Getter;

@Getter
public class HouseholdRegistrationInfoResponse {

    private final String certificateConfirmationNumber;
    private final String certificateTypeCode;
    private final LocalDate date;
    private final String householdName;
    private final LocalDate householdCompositionDate;
    private final String householdCompositionReasonCode;

    public HouseholdRegistrationInfoResponse(Household household, Resident resident,
                                             CertificateIssue certificateIssue) {
        this.certificateConfirmationNumber =
            getFormatCertificationNumber(certificateIssue.getCertificateConfirmationNumber());
        this.certificateTypeCode = certificateIssue.getCertificateTypeCode();
        this.date = certificateIssue.getDate();
        this.householdName = resident.getName();
        this.householdCompositionDate = household.getHouseholdCompositionDate();
        this.householdCompositionReasonCode = household.getHouseholdCompositionReasonCode();
    }

    private String getFormatCertificationNumber(Long number) {
        String n = String.valueOf(number);
        return n.substring(0, 8) + " - " + n.substring(8);
    }
}
