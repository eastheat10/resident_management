package com.nhnacademy.jpa.dto.response.familyrelationship;

import com.nhnacademy.jpa.entity.FamilyRelationship;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class FamilyRelationshipResponse {

    private Long baseSerialNumber;
    private Long residentSerialNumber;
    private String familyRelationshipCode;

    public FamilyRelationshipResponse(FamilyRelationship familyRelationship) {
        this.baseSerialNumber = familyRelationship.getFamilyRelationshipId()
                                                  .getBaseResidentSerialNumber();
        this.residentSerialNumber = familyRelationship.getFamilyRelationshipId()
                                                      .getFamilyResidentSerialNumber();
        this.familyRelationshipCode = familyRelationship.getFamilyRelationshipCode();
    }
}
