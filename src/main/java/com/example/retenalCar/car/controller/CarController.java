package com.example.retenalCar.car.controller;

import com.example.retenalCar.car.entity.Car;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/v1/car")
public class CarController {

    @GetMapping("/available")
    public List<Car> getAvailableCarsDuring(@RequestParam(name = "startDate", required = true) String startDate,
                                            @RequestParam(name = "endDate", required = true) String endDate) {
        return new ArrayList<>();
    }
}
