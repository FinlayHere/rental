package com.example.retenalCar.car.controller;

import com.example.retenalCar.car.entity.Car;
import com.example.retenalCar.car.entity.RentalPeriod;
import com.example.retenalCar.infra.BizException;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static com.example.retenalCar.infra.ErrorCodeEnum.INVALID_PERIOD;

@RestController
@RequestMapping("api/v1/car")
public class CarController {

    @GetMapping("/available")
    public List<Car> getAvailableCarsDuring(@RequestParam(name = "startDate", required = true)
                                            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
                                            @RequestParam(name = "endDate", required = true)
                                            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {

        RentalPeriod period = RentalPeriod.builder()
                .startDate(startDate)
                .endDate(endDate)
                .build();

        if (period.isInvalid()) {
            throw new BizException(INVALID_PERIOD);
        }

        return new ArrayList<>();
    }
}
