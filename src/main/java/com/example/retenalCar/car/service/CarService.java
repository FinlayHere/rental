package com.example.retenalCar.car.service;

import com.example.retenalCar.car.entity.Car;
import com.example.retenalCar.car.entity.RentalPeriod;
import com.example.retenalCar.car.repository.CarRepository;
import com.example.retenalCar.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CarService {
    private final OrderService orderService;
    private final CarRepository carRepository;

    List<Car> findAvailableBy(RentalPeriod period) {
        List<String> conflictOrderIds = orderService.findConflictOrderIds(period);
        if (conflictOrderIds.isEmpty()) {
            return carRepository.findAll();
        }
        return carRepository.findCarsByIdNotIn(conflictOrderIds);
    }
}
