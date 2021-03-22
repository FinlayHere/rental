package com.example.retenalCar.order.repository;

import com.example.retenalCar.order.entity.RentalOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<RentalOrder, Long> {
    @Query("SELECT o FROM RentalOrder o " +
            "WHERE NOT ((o.endDate < :startDate) OR (o.startDate > :endDate))")
    List<RentalOrder> findConflictOrderBy(LocalDate startDate, LocalDate endDate);
}
