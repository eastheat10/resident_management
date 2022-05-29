package com.nhnacademy.jpa.dto.request.familyrelationship;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class FamilyRelationshipRequest {

    private final Long serialNumber;
    private final Long familySerialNumber;
    private final String relationship;
}
