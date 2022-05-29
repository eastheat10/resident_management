package com.nhnacademy.jpa.service;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.nhnacademy.jpa.dto.request.birthdeathreport.BirthDeathReportRequest;
import com.nhnacademy.jpa.dto.response.birthdeathreport.BirthReportResponse;
import com.nhnacademy.jpa.entity.BirthDeathReportResident;
import com.nhnacademy.jpa.entity.Resident;
import com.nhnacademy.jpa.repository.BirthDeathReportRepository;
import com.nhnacademy.jpa.repository.ResidentRepository;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BirthDeathReportServiceTest {

    BirthDeathReportRepository birthDeathRepository;
    ResidentRepository residentRepository;
    BirthDeathReportService service;

    @BeforeEach
    void setUp() {
        birthDeathRepository = mock(BirthDeathReportRepository.class);
        residentRepository = mock(ResidentRepository.class);
        service = new BirthDeathReportService(birthDeathRepository, residentRepository);
    }

    @Test
    @DisplayName("출생정보 추가")
    void birthReportInsert() {

        BirthDeathReportRequest insertRequest = mock(BirthDeathReportRequest.class);
        Resident resident = mock(Resident.class);
        BirthDeathReportResident birthDeathReportResident = mock(BirthDeathReportResident.class);
        BirthDeathReportResident.BirthDeathReportResidentId id =
            mock(BirthDeathReportResident.BirthDeathReportResidentId.class);

        setWhen(insertRequest, resident, birthDeathReportResident, id);

        BirthReportResponse response = service.birthReportInsert(insertRequest);

        assertThat(response).isNotNull();
    }

    @Test
    @DisplayName("출생정보 수정")
    void birthModify() {
        BirthDeathReportRequest request = mock(BirthDeathReportRequest.class);
        BirthDeathReportResident birthDeathReportResident = mock(BirthDeathReportResident.class);

        setWhen(request, mock(Resident.class), birthDeathReportResident, mock(
            BirthDeathReportResident.BirthDeathReportResidentId.class));
        when(birthDeathRepository.findById(any())).thenReturn(Optional.of(birthDeathReportResident));
        doNothing().when(birthDeathReportResident).modify(request);

        BirthReportResponse birthReportResponse = service.birthModify(request);

        assertThat(birthReportResponse).isNotNull();
    }

    @Test
    @DisplayName("출생정보 삭제")
    void birthDelete() {

        BirthDeathReportRequest deleteRequest = mock(BirthDeathReportRequest.class);

        doNothing().when(birthDeathRepository).deleteById(any(BirthDeathReportResident.BirthDeathReportResidentId.class));

        service.birthDelete(deleteRequest);

        verify(birthDeathRepository, times(1)).deleteById(any(BirthDeathReportResident.BirthDeathReportResidentId.class));
    }

    private void setWhen(BirthDeathReportRequest insertRequest, Resident resident,
                           BirthDeathReportResident birthDeathReportResident,
                           BirthDeathReportResident.BirthDeathReportResidentId id) {
        when(residentRepository.findById(anyLong())).thenReturn(Optional.of(resident));
        when(insertRequest.getBirthDeathReportDate()).thenReturn(null);
        when(insertRequest.getReportResidentSerialNumber()).thenReturn(0L);
        when(insertRequest.getResidentSerialNumber()).thenReturn(0L);
        when(insertRequest.getBirthDeathTypeCode()).thenReturn("");
        when(insertRequest.getBirthReportQualificationsCode()).thenReturn("");
        when(insertRequest.getDeathReportQualificationsCode()).thenReturn("");
        when(insertRequest.getEmailAddress()).thenReturn("");
        when(insertRequest.getPhoneNumber()).thenReturn("");

        when(birthDeathRepository.save(any())).thenReturn(birthDeathReportResident);
        when(birthDeathReportResident.getBirthDeathReportResidentId()).thenReturn(id);
        when(id.getBirthDeathTypeCode()).thenReturn("");
        when(id.getReportResidentSerialNumber()).thenReturn(0L);
        when(id.getResidentSerialNumber()).thenReturn(0L);

        when(birthDeathReportResident.getResident()).thenReturn(resident);
        when(birthDeathReportResident.getReportResident()).thenReturn(resident);
        when(resident.getId()).thenReturn(0L);

        when(birthDeathReportResident.getBirthDeathReportDate()).thenReturn(null);
        when(birthDeathReportResident.getBirthReportQualificationsCode()).thenReturn("");
        when(birthDeathReportResident.getEmailAddress()).thenReturn("");
        when(birthDeathReportResident.getPhoneNumber()).thenReturn("");
    }
}