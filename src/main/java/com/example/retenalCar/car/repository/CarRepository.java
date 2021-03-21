package com.example.retenalCar.car.repository;

import com.example.retenalCar.car.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<Car, String> {
    List<Car> findCarsByIdNotIn(List<String> conflictCarId);
}
