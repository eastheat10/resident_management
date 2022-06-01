package com.nhnacademy.jpa.dto.response.resident;

import com.nhnacademy.jpa.entity.dto.ResidentInfo;
import java.util.Objects;
import lombok.Getter;

@Getter
public class ResidentInfoResponse {

    private final Long id;
    private final String name;
    private final boolean hasBirthReport;
    private final boolean hasDeathReport;

    public ResidentInfoResponse(ResidentInfo info) {
        this.id = info.getId();
        this.name = info.getName();
        this.hasBirthReport = Objects.nonNull(info.getBirth());
        this.hasDeathReport = Objects.nonNull(info.getDeath());
    }
}
