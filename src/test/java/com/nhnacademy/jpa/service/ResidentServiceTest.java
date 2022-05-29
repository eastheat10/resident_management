package com.nhnacademy.jpa.service;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.nhnacademy.jpa.dto.request.resident.ResidentRequest;
import com.nhnacademy.jpa.dto.response.resident.ResidentResponse;
import com.nhnacademy.jpa.entity.Resident;
import com.nhnacademy.jpa.repository.ResidentRepository;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ResidentServiceTest {

    ResidentRepository residentRepository;
    ResidentService service;

    @BeforeEach
    void setUp() {
        residentRepository = mock(ResidentRepository.class);
        service = new ResidentService(residentRepository);
    }

    @Test
    @DisplayName("주민 등록")
    void insert() {

        ResidentRequest request = new ResidentRequest();
        Resident resident = new Resident(request);

        when(residentRepository.save(any(Resident.class))).thenReturn(resident);

        ResidentResponse insert = service.insert(request);

        assertThat(insert).isNotNull();
    }

    @Test
    @DisplayName("주민 수정")
    void modify() {

        ResidentRequest request = new ResidentRequest();
        Resident resident = spy(new Resident());

        when(residentRepository.findById(anyLong())).thenReturn(Optional.of(resident));

        doNothing().when(resident).modify(request);

        ResidentResponse modify = service.modify(request, anyLong());

        verify(resident, times(1)).modify(request);
        assertThat(modify).isNotNull();
    }

}