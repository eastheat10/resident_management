package com.nhnacademy.jpa.controller;

import com.nhnacademy.jpa.dto.request.household.HouseholdRequest;
import com.nhnacademy.jpa.dto.response.household.HouseholdResponse;
import com.nhnacademy.jpa.service.HouseholdService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/household")
@RequiredArgsConstructor
public class HouseholdController {

    private final HouseholdService householdService;

    @PostMapping
    public ResponseEntity<HouseholdResponse> insert(@RequestBody HouseholdRequest householdRequest) {

        HouseholdResponse householdResponse = householdService.insert(householdRequest);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        return ResponseEntity.status(HttpStatus.CREATED)
                             .headers(headers)
                             .body(householdResponse);
    }

    @DeleteMapping("/{householdSerialNumber}")
    public ResponseEntity<HouseholdResponse> delete(
        @PathVariable("householdSerialNumber") Long householdSerialNumber) {

        householdService.delete(householdSerialNumber);

        return ResponseEntity.status(HttpStatus.NO_CONTENT)
                             .body(null);
    }
}
