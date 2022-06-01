package com.nhnacademy.jpa.repository;

import com.nhnacademy.jpa.entity.CertificateIssue;
import com.nhnacademy.jpa.entity.Household;
import com.nhnacademy.jpa.entity.HouseholdCompositionResident;
import com.nhnacademy.jpa.entity.Resident;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HouseholdCompositionResidentRepository extends JpaRepository<HouseholdCompositionResident, HouseholdCompositionResident.HouseholdCompositionResidentId> {

    List<HouseholdCompositionResident> findByHousehold(Household household);

}
