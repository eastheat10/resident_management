package com.nhnacademy.jpa.dto.response.household;

import com.nhnacademy.jpa.entity.Household;
import java.time.LocalDate;
import lombok.Getter;

@Getter
public class HouseholdResponse {

    private final Long householdResidentNumber;
    private final LocalDate householdCompositionDate;
    private final String householdCompositionReasonCode;
    private final String currentHouseMovementAddress;

    public HouseholdResponse(Household household) {
        this.householdResidentNumber = household.getResident().getId();
        this.householdCompositionDate = household.getHouseholdCompositionDate();
        this.householdCompositionReasonCode = household.getHouseholdCompositionReasonCode();
        this.currentHouseMovementAddress = household.getCurrentHouseMovementAddress();
    }
}
