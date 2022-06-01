package com.nhnacademy.jpa.repository;

import com.nhnacademy.jpa.entity.dto.BirthReportInfo;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface BirthDeathRepositoryCustom {

    BirthReportInfo findBirthInfoByResidentId(Long id);
}
