package com.nhnacademy.jpa.dto.request.householdmovementaddress;

import java.time.LocalDate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

@Getter
@NoArgsConstructor
public class HouseholdMovementAddressRequest {

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate houseMovementReportDate;
    private String houseMovementAddress;
    private String lastAddressYN;
}
