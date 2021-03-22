package com.example.retenalCar.car.entity;

import com.example.retenalCar.infra.exception.BizException;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

import static com.example.retenalCar.infra.exception.ErrorCodeEnum.INVALID_PERIOD;

@Builder
@Getter
public class RentalPeriod {
    private final LocalDate startDate;
    private final LocalDate endDate;

    private boolean isInvalid() {
        return startDate.isAfter(endDate);
    }

    public void checkValidation() {
        if(isInvalid()){
            throw new BizException(INVALID_PERIOD);
        }
    }
}
