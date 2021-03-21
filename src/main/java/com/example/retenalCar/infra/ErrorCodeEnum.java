package com.example.retenalCar.infra;

import lombok.Getter;
import org.springframework.http.HttpStatus;

public enum ErrorCodeEnum {
    INVALID_PERIOD("10001", "Invalid period start date should before end date", HttpStatus.BAD_REQUEST),
    SYSTEM_ERROR("10999", "System Error", HttpStatus.INTERNAL_SERVER_ERROR);;

    @Getter
    private final String errorCode;
    @Getter
    private final String errorMsg;
    @Getter
    private final HttpStatus responseStatus;

    ErrorCodeEnum(String errorCode, String errorMsg, HttpStatus status) {
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
        this.responseStatus = status;
    }
}