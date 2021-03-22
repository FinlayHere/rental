package com.example.retenalCar.order.service;

import com.example.retenalCar.car.entity.RentalPeriod;
import com.example.retenalCar.infra.exception.BizException;
import com.example.retenalCar.order.command.BookCarCommand;
import com.example.retenalCar.order.entity.RentalOrder;
import com.example.retenalCar.order.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.example.retenalCar.infra.exception.ErrorCodeEnum.CONFLICT_ORDER;
import static com.example.retenalCar.infra.exception.ErrorCodeEnum.INVALID_BOOK_COMMAND;
import static com.example.retenalCar.infra.utils.TimeUtils.toLocalDate;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;

    public List<String> findConflictCarIds(RentalPeriod period) {
        List<RentalOrder> conflictOrder =
                orderRepository.findConflictOrderBy(period.getStartDate(), period.getEndDate());

        return conflictOrder.stream()
                .map(RentalOrder::getCarId)
                .distinct()
                .collect(Collectors.toList());
    }

    public void bookCar(BookCarCommand bookCarCommand, String userId) {
        if (bookCarCommand.isInvalid()) {
            throw new BizException(INVALID_BOOK_COMMAND);
        }

        RentalPeriod period = RentalPeriod.builder()
                .startDate(toLocalDate(bookCarCommand.getStartDate()))
                .endDate(toLocalDate(bookCarCommand.getEndDate()))
                .build();
        period.checkValidation();

        if (findConflictCarIds(period).contains(bookCarCommand.getCarId())) {
            throw new BizException(CONFLICT_ORDER);
        }

        RentalOrder order = RentalOrder.builder()
                .userId(userId)
                .startDate(period.getStartDate())
                .endDate(period.getEndDate())
                .carId(bookCarCommand.getCarId())
                .build();
        orderRepository.save(order);
    }
}
