package com.nhnacademy.jpa.controller;

import com.nhnacademy.jpa.dto.request.familyrelationship.FamilyRelationshipDeleteRequest;
import com.nhnacademy.jpa.dto.request.familyrelationship.FamilyRelationshipInsertRequest;
import com.nhnacademy.jpa.dto.request.familyrelationship.FamilyRelationshipModifyRequest;
import com.nhnacademy.jpa.dto.request.familyrelationship.FamilyRelationshipRequest;
import com.nhnacademy.jpa.dto.response.familyrelationship.FamilyRelationshipResponse;
import com.nhnacademy.jpa.service.FamilyRelationshipService;
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
public class FamilyRelationshipController {

    private final FamilyRelationshipService familyRelationshipService;

    @PostMapping("/{serialNumber}/relationship")
    public ResponseEntity<FamilyRelationshipResponse> insertFamilyRelationship(
        @PathVariable("serialNumber") Long serialNumber,
        @RequestBody FamilyRelationshipInsertRequest insertRequest) {

        FamilyRelationshipRequest relationshipRequest =
            FamilyRelationshipRequest.builder()
                                     .serialNumber(serialNumber)
                                     .familySerialNumber(insertRequest.getFamilySerialNumber())
                                     .relationship(insertRequest.getRelationship())
                                     .build();

        FamilyRelationshipResponse familyRelationshipResponse =
            familyRelationshipService.insertFamilyRelationship(relationshipRequest);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        return ResponseEntity.status(HttpStatus.CREATED)
                             .headers(headers)
                             .body(familyRelationshipResponse);
    }

    @PutMapping("/{serialNumber}/relationship/{familySerialNumber}")
    public ResponseEntity<FamilyRelationshipResponse> modify(
        @PathVariable("serialNumber") Long serialNumber,
        @PathVariable("familySerialNumber") Long familySerialNumber,
        @RequestBody FamilyRelationshipModifyRequest modifyRequest) {

        FamilyRelationshipRequest relationshipRequest =
            FamilyRelationshipRequest.builder()
                                     .serialNumber(serialNumber)
                                     .familySerialNumber(familySerialNumber)
                                     .relationship(modifyRequest.getRelationship())
                                     .build();

        FamilyRelationshipResponse familyRelationshipResponse =
            familyRelationshipService.updateFamilyRelationship(relationshipRequest);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        return ResponseEntity.status(HttpStatus.OK)
                             .headers(headers)
                             .body(familyRelationshipResponse);
    }

    @DeleteMapping("/{serialNumber}/relationship/{familySerialNumber}")
    public ResponseEntity<FamilyRelationshipResponse> delete(
        @PathVariable(name = "serialNumber") Long serialNumber,
        @PathVariable(name = "familySerialNumber") Long familySerialNumber) {

        FamilyRelationshipDeleteRequest deleteRequest =
            FamilyRelationshipDeleteRequest.builder()
                                           .baseSerialNumber(serialNumber)
                                           .residentSerialNumber(familySerialNumber)
                                           .build();
        familyRelationshipService.deleteFamilyRelationship(deleteRequest);

        return ResponseEntity.status(HttpStatus.NO_CONTENT)
                             .body(null);
    }

}
