package com.nhnacademy.jpa.entity;

import com.nhnacademy.jpa.dto.request.resident.ResidentRequest;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

@Table(name = "resident")
@Getter
@Entity
@NoArgsConstructor
public class Resident {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "resident_serial_number")
    private Long id;

    private String name;

    @Column(name = "resident_registration_number")
    private String rrn;

    @Column(name = "gender_code")
    private String genderCode;

    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    @Column(name = "birth_date")
    private LocalDateTime birthDate;

    @Column(name = "birth_place_code")
    private String birthPlaceCode;

    @Column(name = "registration_base_address")
    private String registrationBaseAddress;

    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    @Column(name = "death_date")
    private LocalDateTime deathDate;

    @Column(name = "death_place_code")
    private String deathPlaceCode;

    @Column(name = "death_place_address")
    private String deathPlaceAddress;

    public Resident(ResidentRequest request) {
        this.name = request.getName();
        this.rrn = request.getRrn();
        this.birthDate = request.getBirthDate();
        this.genderCode = request.getGenderCode();
        this.birthPlaceCode = request.getBirthPlaceCode();
        this.registrationBaseAddress = request.getRegistrationBaseAddress();
        this.deathDate = request.getDeathDate();
        this.deathPlaceCode = request.getDeathPlaceCode();
        this.deathPlaceAddress = request.getDeathPlaceAddress();
    }

    public void modify(ResidentRequest request) {
        this.name = request.getName();
        this.rrn = request.getRrn();
        this.birthDate = request.getBirthDate();
        this.genderCode = request.getGenderCode();
        this.birthPlaceCode = request.getBirthPlaceCode();
        this.registrationBaseAddress = request.getRegistrationBaseAddress();
        this.deathDate = request.getDeathDate();
        this.deathPlaceCode = request.getDeathPlaceCode();
        this.deathPlaceAddress = request.getDeathPlaceAddress();
    }
}
