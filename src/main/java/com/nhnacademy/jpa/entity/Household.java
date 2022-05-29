package com.nhnacademy.jpa.entity;

import com.nhnacademy.jpa.dto.request.household.HouseholdRequest;
import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

@Table(name = "household")
@Getter
@Entity
@NoArgsConstructor
public class Household {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "household_serial_number")
    private Long householdSerialNumber;

    @ManyToOne
    @JoinColumn(name = "household_resident_serial_number", referencedColumnName = "resident_serial_number")
    private Resident resident;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "household_composition_date")
    private LocalDate householdCompositionDate;

    @Column(name = "household_composition_reason_code")
    private String householdCompositionReasonCode;

    @Column(name = "current_house_movement_address")
    private String currentHouseMovementAddress;

    public Household(HouseholdRequest request, Resident resident) {
        this.resident = resident;
        this.householdCompositionDate = request.getHouseholdCompositionDate();
        this.householdCompositionReasonCode = request.getHouseholdCompositionReasonCode();
        this.currentHouseMovementAddress = request.getCurrentHouseMovementAddress();
    }
}
