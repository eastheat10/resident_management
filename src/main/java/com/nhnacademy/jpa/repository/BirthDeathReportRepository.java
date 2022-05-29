package com.nhnacademy.jpa.repository;

import com.nhnacademy.jpa.entity.BirthDeathReportResident;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BirthDeathReportRepository extends
    JpaRepository<BirthDeathReportResident, BirthDeathReportResident.BirthDeathReportResidentId> {
}
