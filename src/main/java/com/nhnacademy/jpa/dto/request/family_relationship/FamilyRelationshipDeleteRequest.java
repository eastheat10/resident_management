package com.nhnacademy.jpa.dto.request.family_relationship;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class FamilyRelationshipDeleteRequest {

    private final Long baseSerialNumber;
    private final Long residentSerialNumber;
}
