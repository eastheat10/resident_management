package com.nhnacademy.jpa.dto.response.familyrelationship;

import com.nhnacademy.jpa.repository.dto.FamilyRelationshipInfoDto;
import java.time.LocalDate;
import lombok.Getter;

@Getter
public class FamilyRelationshipInfoResponse {

    private final String familyRelationshipCode;
    private final String name;
    private final LocalDate birthDate;
    private final String residentRegistrationNumber;
    private final String genderCode;

    public FamilyRelationshipInfoResponse(FamilyRelationshipInfoDto dto) {
        this.familyRelationshipCode = dto.getFamilyRelationshipCode();
        this.birthDate = dto.getBirthDate().toLocalDate();
        this.name = dto.getName();
        this.residentRegistrationNumber = rrnMasking(dto.getResidentRegistrationNumber());
        this.genderCode = dto.getGenderCode();
    }

    private String rrnMasking(String rrn) {
        return rrn.substring(0, 7) + "*******";
    }
}
