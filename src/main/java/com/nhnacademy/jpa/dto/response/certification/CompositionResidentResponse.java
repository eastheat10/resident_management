package com.nhnacademy.jpa.dto.response.certification;

import com.nhnacademy.jpa.entity.HouseholdCompositionResident;
import java.time.LocalDate;
import lombok.Getter;

@Getter
public class CompositionResidentResponse {

    private final String residentName;
    private final String relationshipCode;
    private final String rrn;
    private final LocalDate reportDate;
    private final String changeReasonCode;

    public CompositionResidentResponse(HouseholdCompositionResident composition) {

        this.residentName = composition.getResident().getName();
        this.relationshipCode = composition.getHouseholdRelationshipCode();
        this.rrn = rrnMasking(composition.getResident().getRrn());
        this.reportDate = composition.getReportDate();
        this.changeReasonCode = composition.getChangeReasonCode();
    }

    private String rrnMasking(String rrn) {
        return rrn.substring(0, 7) + "*******";
    }
}
