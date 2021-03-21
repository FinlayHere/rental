package com.example.retenalCar.order.command;

import com.example.retenalCar.car.entity.RentalPeriod;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookCarCommand {
    private String carId;
    private RentalPeriod period;
}
