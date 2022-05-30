package com.nhnacademy.jpa.repository;

import com.nhnacademy.jpa.entity.CertificateIssue;
import com.nhnacademy.jpa.entity.Resident;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CertificationRepository extends JpaRepository<CertificateIssue, Long> {

    Optional<CertificateIssue> findByResidentAndCertificateTypeCode(Resident resident, String code);
}
