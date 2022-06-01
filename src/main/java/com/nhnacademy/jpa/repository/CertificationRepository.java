package com.nhnacademy.jpa.repository;

import com.nhnacademy.jpa.entity.CertificateIssue;
import com.nhnacademy.jpa.entity.Resident;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CertificationRepository extends JpaRepository<CertificateIssue, Long> {

    List<CertificateIssue> findByResidentAndCertificateTypeCodeOrderByDateDesc(Resident resident, String code);


    Page<CertificateIssue> findAllByResident(Resident resident, Pageable pageable);
}
