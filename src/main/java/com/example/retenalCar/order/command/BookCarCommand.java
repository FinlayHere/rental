package com.example.retenalCar.order.command;

import lombok.Data;
import org.apache.commons.lang3.StringUtils;

@Data
public class BookCarCommand {
    private String carId;
    private String startDate;
    private String endDate;

    public boolean isInvalid() {
        return StringUtils.isAnyBlank(carId, startDate, endDate);
    }
}
