package com.example.retenalCar.infra;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@EqualsAndHashCode(callSuper = false)
@Data
public class BizException extends RuntimeException implements Serializable {
    private static final long serialVersionUID = -7339807117278109369L;

    private ErrorCodeEnum errorCode = ErrorCodeEnum.SYSTEM_ERROR;
    private String customMsg;
    private String customCode;

    public BizException(ErrorCodeEnum errorCode) {
        super(errorCode.getErrorMsg());
        setErrorCode(errorCode);
        setCustomMsg(errorCode.getErrorMsg());
    }
}
