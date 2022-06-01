package com.nhnacademy.jpa.service;


import com.nhnacademy.jpa.dto.request.resident.ResidentRequest;
import com.nhnacademy.jpa.dto.response.resident.ResidentInfoResponse;
import com.nhnacademy.jpa.dto.response.resident.ResidentResponse;
import com.nhnacademy.jpa.entity.Resident;
import com.nhnacademy.jpa.exception.ResidentNotFound;
import com.nhnacademy.jpa.repository.ResidentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class ResidentService {

    private final ResidentRepository residentRepository;

    public Page<ResidentInfoResponse> findList(Pageable pageable) {

        return residentRepository.findResidents(pageable)
                                 .map(ResidentInfoResponse::new);
    }

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

    public boolean delete(Long id) {
        if (residentRepository.houseCompositionCount(id) > 0) {
            log.info("삭제 실패");
            return false;
        }

        log.info("삭제 = {}", id);
        residentRepository.deleteById(id);
        return true;
    }
}
