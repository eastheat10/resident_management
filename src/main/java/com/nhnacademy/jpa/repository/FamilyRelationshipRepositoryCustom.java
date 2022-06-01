package com.nhnacademy.jpa.repository;

import com.nhnacademy.jpa.entity.dto.ParentInfo;
import java.util.List;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface FamilyRelationshipRepositoryCustom {

    List<ParentInfo> findParent(Long id);

}
