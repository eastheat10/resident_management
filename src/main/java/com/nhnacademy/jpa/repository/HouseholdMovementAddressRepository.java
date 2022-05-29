package com.nhnacademy.jpa.repository;

import com.nhnacademy.jpa.entity.HouseholdMovementAddress;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HouseholdMovementAddressRepository extends
    JpaRepository<HouseholdMovementAddress, HouseholdMovementAddress.HouseholdMovementAddressId> {

    Optional<HouseholdMovementAddress> findByLastAddressYN(String YN);
}
