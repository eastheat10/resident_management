package com.nhnacademy.jpa.repository.dto;

import java.time.LocalDateTime;

public interface FamilyRelationshipInfoDto {

    String getName();

    String getResidentRegistrationNumber();

    String getGenderCode();

    LocalDateTime getBirthDate();

    String getFamilyRelationshipCode();
}
