package com.example.retenalCar.car.service;

import com.example.retenalCar.car.entity.Car;
import com.example.retenalCar.car.entity.RentalPeriod;
import com.example.retenalCar.car.repository.CarRepository;
import com.example.retenalCar.order.service.OrderService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static java.util.Collections.emptyList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = {CarService.class})
public class CarServiceTest {
    @Autowired
    private CarService carService;
    @MockBean
    private CarRepository carRepository;
    @MockBean
    private OrderService orderService;

    private static final int ZERO = 0;
    private static final int THE_NUMBER_OF_COMPANY_CARS = 4;

    private final RentalPeriod PERIOD = RentalPeriod.builder()
            .startDate(LocalDate.of(2020, 3, 21))
            .endDate(LocalDate.of(2020, 3, 22))
            .build();

    private final List<String> CONFLICT_CAR_IDS = Arrays.asList("01", "02");
    private final List<String> ALL_CAR_IDS = Arrays.asList("01", "02", "03", "04");

    private final List<Car> AVAILABLE_CAR = Arrays.asList(Car.builder()
                    .id("01")
                    .model("Toyota Camry")
                    .plateNumber("123")
                    .build(),
            Car.builder()
                    .id("02")
                    .model("Toyota Camry")
                    .plateNumber("124").build());

    private final List<Car> ALL_CARS = Arrays.asList(Car.builder()
                    .id("01")
                    .model("Toyota Camry")
                    .plateNumber("123")
                    .build(),
            Car.builder()
                    .id("02")
                    .model("Toyota Camry")
                    .plateNumber("124").build(),
            Car.builder()
                    .id("03")
                    .model("BMW 650")
                    .plateNumber("125").build(),
            Car.builder()
                    .id("04")
                    .model("BMW 650")
                    .plateNumber("126").build());

    @Test
    void should_return_list_car_info_when_find_available_given_only_part_of_car_has_conflict_order() {
        when(carRepository.findCarsByIdNotIn(CONFLICT_CAR_IDS)).thenReturn(AVAILABLE_CAR);
        when(orderService.findConflictOrderIds(PERIOD)).thenReturn(CONFLICT_CAR_IDS);
        assertThat(carService.findAvailableBy(PERIOD)).isInstanceOf(List.class);
        carService.findAvailableBy(PERIOD).forEach(
                car -> assertThat(car).isInstanceOf(Car.class)
        );
    }

    @Test
    void should_return_empty_list_when_find_available_car_given_all_cars_order_is_conflict() {
        when(orderService.findConflictOrderIds(PERIOD)).thenReturn(ALL_CAR_IDS);
        when(carRepository.findCarsByIdNotIn(CONFLICT_CAR_IDS)).thenReturn(emptyList());
        assertThat(carService.findAvailableBy(PERIOD).size()).isEqualTo(ZERO);
    }

    @Test
    void should_return_all_car_when_find_available_car_given_there_is_no_conflict_order() {
        when(orderService.findConflictOrderIds(PERIOD)).thenReturn(emptyList());
        when(carRepository.findAll()).thenReturn(ALL_CARS);
        assertThat(carService.findAvailableBy(PERIOD).size()).isEqualTo(THE_NUMBER_OF_COMPANY_CARS);
    }
}
