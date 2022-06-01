package com.nhnacademy.jpa.entity.dto;

import java.time.LocalDateTime;

public interface FamilyRelationshipInfoDto {

    String getName();

    String getResidentRegistrationNumber();

    String getGenderCode();

    LocalDateTime getBirthDate();

    String getFamilyRelationshipCode();
}
