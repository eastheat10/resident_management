package com.nhnacademy.jpa.entity;

import com.nhnacademy.jpa.dto.request.householdmovementaddress.HouseholdMovementAddressRequest;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

@Table(name = "household_movement_address")
@Getter
@Entity
@NoArgsConstructor
public class HouseholdMovementAddress {

    @EmbeddedId
    private HouseholdMovementAddressId householdMovementAddressId;

    @MapsId("householdSerialNumber")
    @ManyToOne
    @JoinColumn(name = "household_serial_number")
    private Household household;

    @Column(name = "house_movement_address")
    private String houseMovementAddress;

    @Column(name = "last_address_yn")
    private String lastAddressYN;

    public HouseholdMovementAddress(HouseholdMovementAddressRequest request, Household household) {
        this.householdMovementAddressId =
            new HouseholdMovementAddressId(request.getHouseMovementReportDate(),
                household.getHouseholdSerialNumber());
        this.household = household;
        this.houseMovementAddress = request.getHouseMovementAddress();
        this.lastAddressYN = request.getLastAddressYN();
    }

    public void makeOldAddress() {
        this.lastAddressYN = "N";
    }

    public void modify(HouseholdMovementAddressRequest request) {
        this.houseMovementAddress = request.getHouseMovementAddress();
        this.lastAddressYN = request.getLastAddressYN();
    }

    @Embeddable
    @EqualsAndHashCode
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class HouseholdMovementAddressId implements Serializable {

        @Column(name = "house_movement_report_date")
        private LocalDate houseMovementReportDate;
        private Long householdSerialNumber;
    }
}
