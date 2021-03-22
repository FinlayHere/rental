package com.example.retenalCar.car.controller;

import com.example.retenalCar.car.entity.Car;
import com.example.retenalCar.car.entity.RentalPeriod;
import com.example.retenalCar.car.service.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("api/v1/car")
@RequiredArgsConstructor
public class CarController {
    private final CarService carService;

    @GetMapping("/available")
    public List<Car> getAvailableCarsDuring(@RequestParam(name = "startDate")
                                            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
                                            @RequestParam(name = "endDate")
                                            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {

        RentalPeriod period = RentalPeriod.builder()
                .startDate(startDate)
                .endDate(endDate)
                .build();
        period.checkValidation();

        return carService.findAvailableBy(period);
    }
}
