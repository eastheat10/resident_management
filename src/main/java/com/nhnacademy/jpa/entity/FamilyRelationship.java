package com.nhnacademy.jpa.entity;

import com.nhnacademy.jpa.dto.request.familyrelationship.FamilyRelationshipRequest;
import java.io.Serializable;
import javax.persistence.Column;
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

@Table(name = "family_relationship")
@Getter
@Entity
@NoArgsConstructor
public class FamilyRelationship {

    @EmbeddedId
    private FamilyRelationshipId familyRelationshipId;

    @MapsId("familyResidentSerialNumber")
    @ManyToOne
    @JoinColumn(name = "family_resident_serial_number", referencedColumnName = "resident_serial_number")
    private Resident resident;

    @MapsId("baseResidentSerialNumber")
    @ManyToOne
    @JoinColumn(name = "base_resident_serial_number", referencedColumnName = "resident_serial_number")
    private Resident baseResident;

    @Column(name = "family_relationship_code")
    private String familyRelationshipCode;

    public FamilyRelationship(FamilyRelationshipRequest dto, Resident baseResident) {
        this.familyRelationshipId =
            new FamilyRelationshipId(dto.getSerialNumber(), dto.getFamilySerialNumber());
        this.baseResident = baseResident;
        this.familyRelationshipCode = dto.getRelationship();
    }

    public void updateFamilyRelationshipCode(String familyRelationshipCode) {
        this.familyRelationshipCode = familyRelationshipCode;
    }

    @Embeddable
    @EqualsAndHashCode
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class FamilyRelationshipId implements Serializable {

        private Long familyResidentSerialNumber;
        private Long baseResidentSerialNumber;
    }
}
