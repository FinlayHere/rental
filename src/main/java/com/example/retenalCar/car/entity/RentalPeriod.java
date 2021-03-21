package com.example.retenalCar.car.entity;

import lombok.Builder;
import lombok.Getter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Builder
@Getter
public class RentalPeriod {
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private final LocalDate startDate;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private final LocalDate endDate;

    public boolean isInvalid() {
        return startDate.isAfter(endDate);
    }
}
