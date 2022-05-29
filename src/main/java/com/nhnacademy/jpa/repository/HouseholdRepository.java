package com.nhnacademy.jpa.repository;

import com.nhnacademy.jpa.entity.Household;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HouseholdRepository extends JpaRepository<Household, Long> {
}
