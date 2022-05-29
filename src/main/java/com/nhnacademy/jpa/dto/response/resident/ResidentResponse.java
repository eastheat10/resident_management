package com.nhnacademy.jpa.dto.response.resident;

import com.nhnacademy.jpa.entity.Resident;
import java.time.LocalDateTime;
import lombok.Getter;

@Getter
public class ResidentResponse {

    private final String name;
    private final String rrn;
    private final String genderCode;
    private final LocalDateTime birthDate;
    private final String birthPlaceCode;
    private final String registrationBaseAddress;
    private final LocalDateTime deathDate;
    private final String deathPlaceCode;
    private final String deathPlaceAddress;

    public ResidentResponse(Resident resident) {
        this.name = resident.getName();
        this.rrn = resident.getRrn();
        this.genderCode = resident.getGenderCode();
        this.birthDate = resident.getBirthDate();
        this.birthPlaceCode = resident.getBirthPlaceCode();
        this.registrationBaseAddress = resident.getRegistrationBaseAddress();
        this.deathDate = resident.getDeathDate();
        this.deathPlaceCode = resident.getDeathPlaceCode();
        this.deathPlaceAddress = resident.getDeathPlaceAddress();
    }
}
