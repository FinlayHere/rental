package com.example.retenalCar.car.service;

import com.example.retenalCar.car.entity.Car;
import com.example.retenalCar.car.entity.RentalPeriod;
import com.example.retenalCar.car.repository.CarRepository;
import com.example.retenalCar.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CarService {
    private final OrderService orderService;
    private final CarRepository carRepository;

    List<Car> findAvailableBy(RentalPeriod period) {
        List<String> availableCarsId= orderService.findConflictOrderIds(period);
        if (availableCarsId.isEmpty()){
            return Collections.emptyList();
        }
        return carRepository.findCarsByIdNotIn(availableCarsId);
    }
}
