package com.example.retenalCar.order.controller;

import com.example.retenalCar.car.entity.RentalPeriod;
import com.example.retenalCar.order.command.BookCarCommand;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = OrderController.class)
@ExtendWith(SpringExtension.class)
public class OrderControllerTest {
    @Autowired
    private MockMvc mockMvc;

    private static final String GENERATE_ORDER_CONTROLLER = "/api/v1/order";

    private static final BookCarCommand ORDER_COMMAND = new BookCarCommand("01",
            RentalPeriod.builder()
                    .startDate(LocalDate.of(2021,3,21))
                    .endDate(LocalDate.of(2021,3,22))
                    .build());

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void should_return_201_when_book_rental_given_book_successfully() throws Exception {
        mockMvc.perform(post(GENERATE_ORDER_CONTROLLER)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(ORDER_COMMAND)))
                .andExpect(status().isCreated());
    }
}
