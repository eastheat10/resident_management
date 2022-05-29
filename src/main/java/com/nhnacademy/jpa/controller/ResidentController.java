package com.nhnacademy.jpa.controller;

import com.nhnacademy.jpa.dto.request.resident.ResidentRequest;
import com.nhnacademy.jpa.dto.response.resident.ResidentResponse;
import com.nhnacademy.jpa.service.ResidentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/residents")
public class ResidentController {

    private final ResidentService residentService;

    @PostMapping
    public ResponseEntity<ResidentResponse> insert(@RequestBody ResidentRequest request) {

        ResidentResponse insert = residentService.insert(request);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        return ResponseEntity.status(HttpStatus.CREATED)
                             .headers(headers)
                             .body(insert);
    }

    @PutMapping("/{serialNumber}")
    public ResponseEntity<ResidentResponse> modify(@RequestBody ResidentRequest request,
                                                   @PathVariable("serialNumber") Long serialNumber) {
        ResidentResponse modify = residentService.modify(request, serialNumber);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        return ResponseEntity.status(HttpStatus.OK)
                             .headers(headers)
                             .body(modify);
    }
}
