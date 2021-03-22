package com.example.retenalCar.order.command;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookCarCommand {
    private String carId;
    private String startDate;
    private String endDate;

    public boolean isInvalid() {
        return StringUtils.isAnyBlank(carId, startDate, endDate);
    }
}
