package com.example.retenalCar.infra;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Optional;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(value = {BizException.class})
    public ResponseEntity bizExceptionHandler(BizException e) {

        ErrorCodeEnum error = e.getErrorCode();

        log.error("\n==> Occur BizException - {}", Optional.ofNullable(e.getMessage()).orElse(error.getErrorMsg()));

        String errorMsg = error.getErrorMsg();
        String errorCode = error.getErrorCode();

        if (e.getCustomMsg().isEmpty() || null == e.getCustomMsg()) {
            errorMsg = e.getCustomMsg();
        }

        if (null != e.getCustomCode()) {
            errorCode = e.getCustomCode();
        }

        return new ResponseEntity(ErrorResult.builder()
                .code(errorCode)
                .errMsg(errorMsg).build(),
                error.getResponseStatus());
    }

    @Data
    @Builder
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class ErrorResult {

        private String code;
        private String errMsg;
    }
}
