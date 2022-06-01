package com.nhnacademy.jpa.dto.response.birthdeathreport;

import com.nhnacademy.jpa.entity.dto.ParentInfo;
import lombok.Getter;

@Getter
public class ParentInfoResponse {

    private final String relationship;
    private final String name;
    private final String rrn;

    public ParentInfoResponse(ParentInfo info) {
        this.relationship = info.getRelationship();
        this.name = info.getName();
        this.rrn = rrnMasking(info.getRrn());
    }

    private String rrnMasking(String rrn) {
        return rrn.substring(0, 7) + "*******";
    }

}
