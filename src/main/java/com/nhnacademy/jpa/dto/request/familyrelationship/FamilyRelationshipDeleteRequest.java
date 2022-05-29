package com.nhnacademy.jpa.dto.request.familyrelationship;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class FamilyRelationshipDeleteRequest {

    private final Long baseSerialNumber;
    private final Long residentSerialNumber;
}
