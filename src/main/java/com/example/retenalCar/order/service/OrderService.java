package com.example.retenalCar.order.service;

import com.example.retenalCar.car.entity.RentalPeriod;
import com.example.retenalCar.order.entity.RentalOrder;
import com.example.retenalCar.order.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;

    public List<String> findConflictOrderIds(RentalPeriod period) {
        List<RentalOrder> conflictOrder =
                orderRepository.findConflictOrderBy(period.getStartDate(), period.getEndDate());

        return conflictOrder.stream()
                .map(RentalOrder::getCarId)
                .distinct()
                .collect(Collectors.toList());
    }
}
