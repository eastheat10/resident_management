package com.nhnacademy.jpa.service;

import com.nhnacademy.jpa.dto.request.resident.ResidentRequest;
import com.nhnacademy.jpa.dto.response.resident.ResidentResponse;
import com.nhnacademy.jpa.entity.Resident;
import com.nhnacademy.jpa.exception.ResidentNotFound;
import com.nhnacademy.jpa.repository.ResidentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ResidentService {

    private final ResidentRepository residentRepository;

    @Transactional
    public ResidentResponse insert(ResidentRequest request) {

        Resident resident = new Resident(request);

        Resident savedResident = residentRepository.save(resident);

        return new ResidentResponse(savedResident);
    }

    @Transactional
    public ResidentResponse modify(ResidentRequest request, Long id) {
        Resident resident = residentRepository.findById(id).orElseThrow(ResidentNotFound::new);

        resident.modify(request);

        return new ResidentResponse(resident);
    }
}
