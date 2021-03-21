package com.example.retenalCar.order.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDate;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RentalOrder {
    @Id
    private String id;
    private String carId;
    private String userId;
    private LocalDate startDate;
    private LocalDate endDate;
}
