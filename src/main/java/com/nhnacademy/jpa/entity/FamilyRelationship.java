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

    @MapsId("baseResidentSerialNumber")
    @ManyToOne
    @JoinColumn(name = "base_resident_serial_number", referencedColumnName = "resident_serial_number")
    private Resident resident;

    @Column(name = "family_relationship_code")
    private String familyRelationshipCode;

    public FamilyRelationship(FamilyRelationshipRequest dto, Resident resident) {
        this.familyRelationshipId =
            new FamilyRelationshipId(dto.getSerialNumber(), dto.getFamilySerialNumber());
        this.resident = resident;
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

        private Long baseResidentSerialNumber;

        @Column(name = "family_resident_serial_number")
        private Long familyResidentSerialNumber;
    }
}
