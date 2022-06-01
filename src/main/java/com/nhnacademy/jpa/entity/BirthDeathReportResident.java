package com.nhnacademy.jpa.entity;

import com.nhnacademy.jpa.dto.request.birthdeathreport.BirthDeathReportRequest;
import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "birth_death_report_resident")
@Getter
@Entity
@NoArgsConstructor
public class BirthDeathReportResident {

    @EmbeddedId
    private BirthDeathReportResidentId birthDeathReportResidentId;

    @MapsId("reportResidentSerialNumber")
    @ManyToOne
    @JoinColumn(name = "report_resident_serial_number")
    private Resident reportResident;

    @MapsId("residentSerialNumber")
    @ManyToOne
    @JoinColumn(name = "resident_serial_number")
    private Resident resident;

    @Column(name = "birth_death_report_date")
    private LocalDate birthDeathReportDate;

    @Column(name = "birth_report_qualifications_code")
    private String birthReportQualificationsCode;

    @Column(name = "death_report_qualifications_code")
    private String deathReportQualificationsCode;

    @Column(name = "email_address")
    private String emailAddress;

    @Column(name = "phone_number")
    private String phoneNumber;

    public BirthDeathReportResident(BirthDeathReportRequest request,
                                    Resident resident, Resident reportResident) {
        this.birthDeathReportResidentId =
            BirthDeathReportResidentId.builder()
                                      .birthDeathTypeCode(request.getBirthDeathTypeCode())
                                      .reportResidentSerialNumber(
                                          request.getReportResidentSerialNumber())
                                      .residentSerialNumber(request.getResidentSerialNumber())
                                      .build();
        this.reportResident = reportResident;
        this.resident = resident;
        this.birthDeathReportDate = request.getBirthDeathReportDate();
        this.birthReportQualificationsCode = request.getBirthReportQualificationsCode();
        this.deathReportQualificationsCode = request.getDeathReportQualificationsCode();
        this.emailAddress = request.getEmailAddress();
        this.phoneNumber = request.getPhoneNumber();
    }

    public void modify(BirthDeathReportRequest updateRequest) {
        this.birthDeathReportDate = updateRequest.getBirthDeathReportDate();
        this.birthReportQualificationsCode = updateRequest.getBirthReportQualificationsCode();
        this.emailAddress = updateRequest.getEmailAddress();
        this.phoneNumber = updateRequest.getPhoneNumber();
    }

    @Embeddable
    @EqualsAndHashCode
    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class BirthDeathReportResidentId implements Serializable {

        @Column(name = "birth_death_type_code")
        private String birthDeathTypeCode;

        private Long reportResidentSerialNumber;
        private Long residentSerialNumber;
    }
}
