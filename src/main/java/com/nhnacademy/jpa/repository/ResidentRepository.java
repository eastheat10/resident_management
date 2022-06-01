package com.nhnacademy.jpa.repository;

import com.nhnacademy.jpa.entity.Resident;
import com.nhnacademy.jpa.entity.dto.ResidentInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ResidentRepository extends JpaRepository<Resident, Long> {

    @Query(value = "SELECT r.name AS name, " +
        "   r.id AS id, " +
        "   (SELECT b1.birthDeathReportResidentId.residentSerialNumber " +
        "    FROM BirthDeathReportResident b1 " +
        "    WHERE b1.birthDeathReportResidentId.residentSerialNumber = r.id " +
        "    AND b1.birthDeathReportResidentId.birthDeathTypeCode = '출생') AS birth, " +
        "   (SELECT b1.birthDeathReportResidentId.residentSerialNumber " +
        "    FROM BirthDeathReportResident b1 " +
        "    WHERE b1.birthDeathReportResidentId.residentSerialNumber = r.id " +
        "    AND b1.birthDeathReportResidentId.birthDeathTypeCode = '사망') AS death " +
        "FROM Resident r",
        countQuery = "SELECT count(r.id) FROM Resident  r")
    Page<ResidentInfo> findResidents(Pageable pageable);

    @Query("SELECT COUNT(r.id) " +
        "   FROM Resident r" +
        "       INNER JOIN Household h" +
        "           ON r.id = h.resident.id" +
        "       INNER JOIN HouseholdCompositionResident hcr" +
        "           ON h.householdSerialNumber = hcr.household.householdSerialNumber " +
        "WHERE r.id = :id")
    Long houseCompositionCount(@Param("id") Long id);
}
