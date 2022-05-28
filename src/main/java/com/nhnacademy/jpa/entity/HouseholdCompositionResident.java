package com.nhnacademy.jpa.entity;

import java.io.Serializable;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "household_composition_resident")
@Getter
@Entity
@NoArgsConstructor
public class HouseholdCompositionResident {

    @EmbeddedId
    private HouseholdCompositionResidentId householdCompositionResidentId;

    @MapsId("householdSerialNumber")
    @ManyToOne
    @JoinColumn(name = "household_serial_number")
    private Household household;

    @MapsId("residentSerialNumber")
    @ManyToOne
    @JoinColumn(name = "resident_serial_number")
    private Resident resident;

    @Embeddable
    @EqualsAndHashCode
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class HouseholdCompositionResidentId implements Serializable {

        private Long householdSerialNumber;
        private Long residentSerialNumber;
    }
}
