package com.nhnacademy.jpa.controller;

import com.nhnacademy.jpa.dto.request.household.HouseholdRequest;
import com.nhnacademy.jpa.dto.request.householdmovementaddress.HouseholdMovementAddressRequest;
import com.nhnacademy.jpa.dto.response.household.HouseholdResponse;
import com.nhnacademy.jpa.dto.response.householdmovementaddress.HouseholdMovementAddressResponse;
import com.nhnacademy.jpa.service.HouseholdService;
import java.time.LocalDate;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/household")
@RequiredArgsConstructor
public class HouseholdController {

    private final HouseholdService householdService;

    @PostMapping
    public ResponseEntity<HouseholdResponse> insert(
        @RequestBody HouseholdRequest householdRequest) {

        HouseholdResponse householdResponse = householdService.insertHousehold(householdRequest);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        return ResponseEntity.status(HttpStatus.CREATED)
                             .headers(headers)
                             .body(householdResponse);
    }

    @DeleteMapping("/{householdSerialNumber}")
    public ResponseEntity<HouseholdResponse> delete(
        @PathVariable("householdSerialNumber") Long householdSerialNumber) {

        householdService.deleteHousehold(householdSerialNumber);

        return ResponseEntity.status(HttpStatus.NO_CONTENT)
                             .body(null);
    }

    @PostMapping("/{householdSerialNumber}/movement")
    public ResponseEntity<HouseholdMovementAddressResponse> insertMovementAddress(
        @RequestBody HouseholdMovementAddressRequest insertRequest,
        @PathVariable("householdSerialNumber") Long householdSerialNumber) {

        HouseholdMovementAddressResponse householdMovementAddressResponse =
            householdService.insertMovementAddress(insertRequest, householdSerialNumber);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        return ResponseEntity.status(HttpStatus.CREATED)
                             .headers(headers)
                             .body(householdMovementAddressResponse);
    }

    @PutMapping("/{householdSerialNumber}/movement/{reportDate}")
    public ResponseEntity<HouseholdMovementAddressResponse> modifyMovementAddress(
        @RequestBody HouseholdMovementAddressRequest modifyRequest,
        @PathVariable("householdSerialNumber") Long householdSerialNumber,
        @PathVariable("reportDate") @DateTimeFormat(pattern = "yyyyMMdd") LocalDate reportDate) {

        HouseholdMovementAddressResponse householdMovementAddressResponse =
            householdService.updateMovementAddress(modifyRequest, householdSerialNumber, reportDate);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        return ResponseEntity.status(HttpStatus.OK)
                             .headers(headers)
                             .body(householdMovementAddressResponse);
    }

    @DeleteMapping("/{householdSerialNumber}/movement/{reportDate}")
    public ResponseEntity<HouseholdMovementAddressResponse> deleteMovementAddress(
        @PathVariable("householdSerialNumber") Long householdSerialNumber,
        @PathVariable("reportDate") @DateTimeFormat(pattern = "yyyyMMdd") LocalDate reportDate) {

        householdService.deleteMovementAddress(householdSerialNumber, reportDate);

        return ResponseEntity.status(HttpStatus.NO_CONTENT)
                             .body(null);
    }
}
