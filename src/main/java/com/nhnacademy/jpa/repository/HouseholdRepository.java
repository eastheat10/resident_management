package com.nhnacademy.jpa.repository;

import com.nhnacademy.jpa.entity.Household;
import com.nhnacademy.jpa.entity.Resident;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HouseholdRepository extends JpaRepository<Household, Long> {

    Optional<Household> findByResident(Resident resident);
}
