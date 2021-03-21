package com.example.retenalCar.car.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = CarController.class)
@ExtendWith(SpringExtension.class)
public class CarControllerTest {
    @Autowired
    private MockMvc mockMvc;

    private static final String GET_CAR_URL = "/api/v1/car/available";

    @Test
    void should_return_cars_when_get_car_given_start_date_and_end_date() throws Exception {
        mockMvc.perform(get(GET_CAR_URL)
                .param("startDate", "2021-03-23")
                .param("endDate", "2021-03-25"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray());
    }

    @Test
    void should_return_400_when_call_api_given_only_start_date() throws Exception {
        mockMvc.perform(get(GET_CAR_URL)
                .param("startDate", "2021-03-23"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void should_return_400_when_call_api_given_start_date_is_after_end_date() throws Exception {
        mockMvc.perform(get(GET_CAR_URL)
                .param("startDate", "2021-03-24")
                .param("endDate", "2021-03-21"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.code").value(10001));
    }
}
