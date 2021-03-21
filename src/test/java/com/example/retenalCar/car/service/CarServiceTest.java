package com.example.retenalCar.car.service;

import com.example.retenalCar.car.entity.Car;
import com.example.retenalCar.car.entity.RentalPeriod;
import com.example.retenalCar.car.repository.CarRepository;
import com.example.retenalCar.order.entity.RentalOrder;
import com.example.retenalCar.order.repository.OrderRepository;
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

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsInstanceOf.instanceOf;
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

    private final RentalPeriod PERIOD = RentalPeriod.builder()
            .startDate(LocalDate.of(2020, 3, 21))
            .endDate(LocalDate.of(2020, 3, 22))
            .build();

    private final List<String> CONFLICT_CAR_IDS = Arrays.asList("01", "02");

    private final List<Car> AVAILABLE_CAR = Arrays.asList(Car.builder()
                    .id("01")
                    .model("Toyota Camry")
                    .plateNumber("123")
                    .build(),
            Car.builder()
                    .id("02")
                    .model("Toyota Camry")
                    .plateNumber("123").build());

    private final List<RentalOrder> CONFLICT_ORDER = Arrays.asList(
            RentalOrder.builder()
                    .id("01")
                    .carId("03")
                    .startDate(LocalDate.of(2020, 3, 19))
                    .endDate(LocalDate.of(2020, 3, 22))
                    .userId("01")
                    .build(),
            RentalOrder.builder()
                    .id("02")
                    .carId("04")
                    .startDate(LocalDate.of(2020, 3, 19))
                    .endDate(LocalDate.of(2020, 3, 25))
                    .userId("01")
                    .build()
    );

    @Test
    void should_return_list_car_info_when_find_available_given_only_part_of_car_has_conflict_order() {
        when(carRepository.findCarsByIdNotIn(CONFLICT_CAR_IDS)).thenReturn(AVAILABLE_CAR);
        when(orderService.findConflictOrderIds(PERIOD)).thenReturn(CONFLICT_CAR_IDS);
        assertThat(carService.findAvailableBy(PERIOD), instanceOf(List.class));
        carService.findAvailableBy(PERIOD).forEach(
                car -> assertThat(car, instanceOf(Car.class))
        );
    }
}
