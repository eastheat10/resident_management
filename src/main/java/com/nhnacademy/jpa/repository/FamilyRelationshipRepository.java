package com.nhnacademy.jpa.repository;

import com.nhnacademy.jpa.entity.FamilyRelationship;
import com.nhnacademy.jpa.repository.dto.FamilyRelationshipInfoDto;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface FamilyRelationshipRepository
    extends JpaRepository<FamilyRelationship, FamilyRelationship.FamilyRelationshipId> {

    @Query("SELECT r.name AS name" +
        ", r.rrn AS residentRegistrationNumber" +
        ", r.genderCode AS genderCode" +
        ", r.birthDate AS birthDate" +
        ",f.familyRelationshipCode AS familyRelationshipCode " +
        "FROM Resident r JOIN FETCH FamilyRelationship f " +
        "ON r.id = f.resident.id " +
        "WHERE f.familyRelationshipId.baseResidentSerialNumber = :id " +
        "ORDER BY r.birthDate")
    List<FamilyRelationshipInfoDto> findFamilyRelationshipByBaseResident(@Param("id") Long id);
}
