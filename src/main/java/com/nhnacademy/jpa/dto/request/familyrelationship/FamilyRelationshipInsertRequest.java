package com.nhnacademy.jpa.dto.request.familyrelationship;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class FamilyRelationshipInsertRequest {

    private Long familySerialNumber;
    private String relationship;
}
