package com.nhnacademy.jpa.repository;

import com.nhnacademy.jpa.entity.BirthDeathReportResident;
import com.nhnacademy.jpa.entity.dto.BirthReportInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BirthDeathReportRepository extends
    JpaRepository<BirthDeathReportResident,  BirthDeathReportResident.BirthDeathReportResidentId> {

    @Query("SELECT b.birthDeathReportDate AS reportedDate, " +
        "          b.birthReportQualificationsCode AS reportResidentQualification,  " +
        "          b.phoneNumber AS reportResidentPhoneNumber, " +
        "          b.emailAddress AS reportResidentEmail, " +
        "          r.birthDate AS birthDate, " +
        "          r.name AS name, " +
        "          r.genderCode AS genderCode, " +
        "          r.birthPlaceCode AS birthPlaceCode, " +
        "          r.registrationBaseAddress AS residentBaseAddress, " +
        "          r.rrn AS rrn, " +
        "          reportResident.name AS reportResidentName, " +
        "          reportResident.rrn AS reportResidentRrn " +
        "FROM BirthDeathReportResident b " +
        "   INNER JOIN Resident r " +
        "       ON b.birthDeathReportResidentId.residentSerialNumber = r.id " +
        "   INNER JOIN Resident  reportResident " +
        "       ON b.birthDeathReportResidentId.reportResidentSerialNumber = reportResident.id " +
        "WHERE b.birthDeathReportResidentId.residentSerialNumber = :id")
    BirthReportInfo findBirthInfoByResidentId(@Param("id") Long id);

}
