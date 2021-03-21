package com.example.retenalCar.car.entity;

import lombok.Builder;

import java.time.LocalDate;

@Builder
public class RentalPeriod {
    private final LocalDate startDate;
    private final LocalDate endDate;

    public boolean isInvalid() {
        return startDate.isAfter(endDate);
    }
}
