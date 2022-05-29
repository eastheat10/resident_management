package com.nhnacademy.jpa.dto.response.householdmovementaddress;

import com.nhnacademy.jpa.entity.HouseholdMovementAddress;
import java.time.LocalDate;
import lombok.Getter;

@Getter
public class HouseholdMovementAddressResponse {

    private final LocalDate houseMovementReportDate;
    private final String houseMovementAddress;
    private final String lastAddressYN;

    public HouseholdMovementAddressResponse(HouseholdMovementAddress movementAddress) {
        this.houseMovementReportDate = movementAddress.getHouseholdMovementAddressId()
                                                      .getHouseMovementReportDate();
        this.houseMovementAddress = movementAddress.getHouseMovementAddress();
        this.lastAddressYN = movementAddress.getLastAddressYN();
    }
}
