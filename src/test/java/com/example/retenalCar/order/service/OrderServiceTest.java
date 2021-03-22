package com.example.retenalCar.order.service;

import com.example.retenalCar.car.entity.RentalPeriod;
import com.example.retenalCar.infra.exception.BizException;
import com.example.retenalCar.order.command.BookCarCommand;
import com.example.retenalCar.order.entity.RentalOrder;
import com.example.retenalCar.order.repository.OrderRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static com.example.retenalCar.infra.exception.ErrorCodeEnum.CONFLICT_ORDER;
import static com.example.retenalCar.infra.exception.ErrorCodeEnum.INVALID_BOOK_COMMAND;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = {OrderService.class})
public class OrderServiceTest {
    @Autowired
    private OrderService orderService;
    @MockBean
    private OrderRepository orderRepository;

    private static final LocalDate START_DATE = LocalDate.of(2020, 3, 21);
    private static final LocalDate END_DATE = LocalDate.of(2020, 3, 22);
    private static final RentalPeriod PERIOD = RentalPeriod.builder().startDate(START_DATE).endDate(END_DATE).build();

    private static final List<RentalOrder> CONFLICT_ORDERS = Arrays.asList(
            RentalOrder.builder()
                    .carId("01")
                    .build(),
            RentalOrder.builder()
                    .carId("01")
                    .build(),
            RentalOrder.builder()
                    .carId("02")
                    .build(),
            RentalOrder.builder()
                    .carId("02")
                    .build()
    );

    private static final BookCarCommand VALID_BOOK_CAR_COMMAND =
            new BookCarCommand("03", "2020-03-22", "2020-03-24");

    private static final BookCarCommand CONFLICT_BOOK_CAR_COMMAND =
            new BookCarCommand("01", "2020-03-22", "2020-03-24");

    private static final BookCarCommand INVALID_BOOK_CAR_COMMAND =
            new BookCarCommand("01", "", "2020-03-24");

    @Test
    void should_cars_id_distinct_when_find_conflict_car_id_given_repeat_car_id_from_db_select_result() {
        when(orderRepository.findConflictOrderBy(START_DATE, END_DATE)).thenReturn(CONFLICT_ORDERS);
        assertThat(orderService.findConflictCarIds(PERIOD).size()).isEqualTo(2);
    }

    @Test
    void should_save_order_info_when_book_rental_given_book_command_valid_and_car_id_is_not_conflict() {
        when(orderRepository.findConflictOrderBy(any(), any())).thenReturn(CONFLICT_ORDERS);
        orderService.bookCar(VALID_BOOK_CAR_COMMAND, "01");
        verify(orderRepository).save(any());
    }

    @Test
    void should_throw_biz_exception_INVALID_BOOK_COMMAND_when_book_car_given_invalid_book_command() {
        when(orderRepository.findConflictOrderBy(any(), any())).thenReturn(CONFLICT_ORDERS);
        BizException exception
                = assertThrows(BizException.class, () -> orderService.bookCar(INVALID_BOOK_CAR_COMMAND, "01"));
        assertThat(exception.getErrorCode()).isEqualTo(INVALID_BOOK_COMMAND);
    }

    @Test
    void should_throw_biz_exception_CONFLICT_ORDER_when_book_car_given_car_id_conflict() {
        when(orderRepository.findConflictOrderBy(any(), any())).thenReturn(CONFLICT_ORDERS);
        BizException exception
                = assertThrows(BizException.class, () -> orderService.bookCar(CONFLICT_BOOK_CAR_COMMAND, "01"));
        assertThat(exception.getErrorCode()).isEqualTo(CONFLICT_ORDER);
    }
}
