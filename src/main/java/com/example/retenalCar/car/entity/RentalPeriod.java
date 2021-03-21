package com.example.retenalCar.car.entity;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Builder
@Getter
public class RentalPeriod {
    private final LocalDate startDate;
    private final LocalDate endDate;

    public boolean isInvalid() {
        return startDate.isAfter(endDate);
    }
}
