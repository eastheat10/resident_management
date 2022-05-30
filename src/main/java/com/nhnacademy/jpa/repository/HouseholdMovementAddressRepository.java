package com.nhnacademy.jpa.repository;

import com.nhnacademy.jpa.entity.Household;
import com.nhnacademy.jpa.entity.HouseholdMovementAddress;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface HouseholdMovementAddressRepository extends
    JpaRepository<HouseholdMovementAddress, HouseholdMovementAddress.HouseholdMovementAddressId> {

    Optional<HouseholdMovementAddress> findByLastAddressYN(String YN);

    @Query("SELECT h from HouseholdMovementAddress h " +
        "WHERE h.household = :household " +
        "ORDER BY h.householdMovementAddressId.houseMovementReportDate DESC")
    List<HouseholdMovementAddress> findByHousehold(@Param("household") Household household);
}
