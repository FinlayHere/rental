package com.example.retenalCar.order.controller;

import com.example.retenalCar.order.command.BookCarCommand;
import com.example.retenalCar.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/order")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @PostMapping("")
    @ResponseStatus(value = HttpStatus.CREATED)
    public void bookCar(@RequestBody BookCarCommand bookCarCommand,
                        @RequestHeader(value = "userId") String userId) {
        orderService.bookCar(bookCarCommand, userId);
    }

}
