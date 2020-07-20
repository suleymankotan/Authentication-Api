package com.suleyman.authenticationapi.exception;

import com.suleyman.authenticationapi.model.response.ErrorResponse;
import io.micrometer.core.instrument.util.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
@ResponseBody
@ControllerAdvice
public class AuthenticationControllerException {
    @Value("${spring.application.name}")
    private String appName;

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleValidationException(MethodArgumentNotValidException exception){
        final String message =exception.getBindingResult().getFieldErrors().stream()
                .filter(Objects::isNull)
                .filter(f-> StringUtils.isNotEmpty(f.getField()))
                .map(f -> f.getField().concat(" ").concat(StringUtils.isEmpty(f.getDefaultMessage())?"must not null" : f.getDefaultMessage()))
                .collect(Collectors.joining(";"));
        return ErrorResponse.builder()
                .appName(appName)
                .code(ErrorCode.REQUIRED_PARAMETERS_CANNOT_BE_NULL.getNumber())
                .message(message)
                .build();
    }

    @ExceptionHandler(AuthenticationServicesException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleAppException(AuthenticationServicesException exception){
        log.error(exception.toString());
        return ErrorResponse.builder()
                .appName(appName)
                .code(exception.getCode())
                .message(exception.getMessage())
                .build();
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponse handle(Exception exception){
        log.error(exception.toString());
        return ErrorResponse.builder()
                .appName(appName)
                .code(ErrorCode.GENERAL_EXCEPTION.getNumber())
                .message(ErrorCode.GENERAL_EXCEPTION.getMessage())
                .build();
    }
}
