package com.nhnacademy.jpa.repository.impl;

import com.nhnacademy.jpa.entity.FamilyRelationship;
import com.nhnacademy.jpa.entity.QFamilyRelationship;
import com.nhnacademy.jpa.entity.QResident;
import com.nhnacademy.jpa.entity.dto.ParentInfo;
import com.nhnacademy.jpa.entity.dto.QParentInfo;
import com.nhnacademy.jpa.repository.FamilyRelationshipRepositoryCustom;
import java.util.List;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

public class FamilyRelationshipRepositoryImpl extends QuerydslRepositorySupport
    implements FamilyRelationshipRepositoryCustom {

    public FamilyRelationshipRepositoryImpl() {
        super(FamilyRelationship.class);
    }

    @Override
    public List<ParentInfo> findParent(Long id) {

        QFamilyRelationship f = QFamilyRelationship.familyRelationship;
        QResident r = QResident.resident;

        return from(f)
            .innerJoin(f.resident, r)
            .where(f.baseResident.id.eq(id))
            .select(new QParentInfo(f.familyRelationshipCode, r.name, r.rrn))
            .fetch();
    }
}
