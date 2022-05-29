package com.nhnacademy.jpa.controller;

import static com.nhnacademy.jpa.service.BirthDeathReportService.*;

import com.nhnacademy.jpa.dto.request.birthdeathreport.DeathReportModifyRequest;
import com.nhnacademy.jpa.dto.request.birthdeathreport.BirthDeathReportRequest;
import com.nhnacademy.jpa.dto.request.birthdeathreport.BirthReportInsertRequest;
import com.nhnacademy.jpa.dto.request.birthdeathreport.BirthReportModifyRequest;
import com.nhnacademy.jpa.dto.request.birthdeathreport.DeathReportInsertRequest;
import com.nhnacademy.jpa.dto.response.birthdeathreport.BirthReportResponse;
import com.nhnacademy.jpa.dto.response.birthdeathreport.DeathReportResponse;
import com.nhnacademy.jpa.service.BirthDeathReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/residents")
@RequiredArgsConstructor
public class BirthDeathReportController {

    private final BirthDeathReportService birthDeathReportService;

    @PostMapping("{serialNumber}/birth")
    public ResponseEntity<BirthReportResponse> insert(
        @RequestBody BirthReportInsertRequest insertRequest,
        @PathVariable("serialNumber") Long serialNumber) {

        BirthDeathReportRequest request =
            BirthDeathReportRequest.builder()
                                   .birthDeathTypeCode(BIRTH)
                                   .reportResidentSerialNumber(serialNumber)
                                   .residentSerialNumber(
                                       insertRequest.getResidentSerialNumber())
                                   .birthDeathReportDate(insertRequest.getBirthDeathReportDate())
                                   .birthReportQualificationsCode(
                                       insertRequest.getBirthReportQualificationsCode())
                                   .emailAddress(insertRequest.getEmailAddress())
                                   .phoneNumber(insertRequest.getPhoneNumber())
                                   .build();

        BirthReportResponse birthReportResponse =
            birthDeathReportService.birthReportInsert(request);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        return ResponseEntity.status(HttpStatus.CREATED)
                             .headers(headers)
                             .body(birthReportResponse);
    }

    @PutMapping("/{serialNumber}/birth/{targetSerialNumber}")
    public ResponseEntity<BirthReportResponse> birthModify(
        @RequestBody BirthReportModifyRequest modifyRequest,
        @PathVariable("serialNumber") Long serialNumber,
        @PathVariable("targetSerialNumber") Long targetSerialNumber) {

        BirthDeathReportRequest request =
            BirthDeathReportRequest.builder()
                                   .reportResidentSerialNumber(serialNumber)
                                   .residentSerialNumber(targetSerialNumber)
                                   .birthDeathReportDate(modifyRequest.getBirthDeathReportDate())
                                   .birthReportQualificationsCode(
                                       modifyRequest.getBirthReportQualificationsCode())
                                   .emailAddress(modifyRequest.getEmailAddress())
                                   .phoneNumber(modifyRequest.getPhoneNumber())
                                   .build();

        BirthReportResponse birthReportResponse = birthDeathReportService.birthModify(request);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        return ResponseEntity.status(HttpStatus.OK)
                             .headers(headers)
                             .body(birthReportResponse);
    }

    @DeleteMapping("/{serialNumber}/birth/{targetSerialNumber}")
    public ResponseEntity<BirthReportResponse> birthDelete(
        @PathVariable("serialNumber") Long serialNumber,
        @PathVariable("targetSerialNumber") Long targetSerialNumber) {

        BirthDeathReportRequest request =
            BirthDeathReportRequest.builder()
                                   .birthDeathTypeCode(BIRTH)
                                   .residentSerialNumber(targetSerialNumber)
                                   .reportResidentSerialNumber(serialNumber)
                                   .build();

        birthDeathReportService.birthDelete(request);

        return ResponseEntity.status(HttpStatus.NO_CONTENT)
                             .body(null);
    }

    @PostMapping("/{serialNumber}/death")
    public ResponseEntity<DeathReportResponse> deathInsert(
        @RequestBody DeathReportInsertRequest insertRequest,
        @PathVariable("serialNumber") Long serialNumber) {

        BirthDeathReportRequest request =
            BirthDeathReportRequest.builder()
                                   .birthDeathTypeCode(DEATH)
                                   .reportResidentSerialNumber(serialNumber)
                                   .residentSerialNumber(
                                       insertRequest.getResidentSerialNumber())
                                   .birthDeathReportDate(insertRequest.getBirthDeathReportDate())
                                   .deathReportQualificationsCode(
                                       insertRequest.getDeathReportQualificationsCode())
                                   .emailAddress(insertRequest.getEmailAddress())
                                   .phoneNumber(insertRequest.getPhoneNumber())
                                   .build();

        DeathReportResponse deathReportResponse =
            birthDeathReportService.deathReportInsert(request);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        return ResponseEntity.status(HttpStatus.CREATED)
                             .headers(headers)
                             .body(deathReportResponse);
    }

    @PutMapping("/{serialNumber}/death/{targetSerialNumber}")
    public ResponseEntity<DeathReportResponse> deathModify(
        @RequestBody DeathReportModifyRequest modifyRequest,
        @PathVariable("serialNumber") Long serialNumber,
        @PathVariable("targetSerialNumber") Long targetSerialNumber) {

        BirthDeathReportRequest request =
            BirthDeathReportRequest.builder()
                                   .reportResidentSerialNumber(serialNumber)
                                   .residentSerialNumber(targetSerialNumber)
                                   .birthDeathReportDate(modifyRequest.getBirthDeathReportDate())
                                   .deathReportQualificationsCode(
                                       modifyRequest.getDeathReportQualificationsCode())
                                   .emailAddress(modifyRequest.getEmailAddress())
                                   .phoneNumber(modifyRequest.getPhoneNumber())
                                   .build();

        DeathReportResponse deathReportResponse = birthDeathReportService.deathReportModify(request);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        return ResponseEntity.status(HttpStatus.OK)
                             .headers(headers)
                             .body(deathReportResponse);
    }

    @DeleteMapping("/{serialNumber}/death/{targetSerialNumber}")
    public ResponseEntity<DeathReportResponse> deathDelete(
        @PathVariable("serialNumber") Long serialNumber,
        @PathVariable("targetSerialNumber") Long targetSerialNumber) {

        BirthDeathReportRequest request =
            BirthDeathReportRequest.builder()
                                   .birthDeathTypeCode(BIRTH)
                                   .residentSerialNumber(targetSerialNumber)
                                   .reportResidentSerialNumber(serialNumber)
                                   .build();

        birthDeathReportService.deathDelete(request);

        return ResponseEntity.status(HttpStatus.NO_CONTENT)
                             .body(null);
    }
}
