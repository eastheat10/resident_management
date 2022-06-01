package com.nhnacademy.jpa.entity.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ParentInfo {

    private String relationship;
    private String name;
    private String rrn;

    @QueryProjection
    public ParentInfo(String relationship, String name, String rrn) {
        this.relationship = relationship;
        this.name = name;
        this.rrn = rrn;
    }
}
