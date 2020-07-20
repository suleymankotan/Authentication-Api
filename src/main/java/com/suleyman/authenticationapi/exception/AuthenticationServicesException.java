package com.suleyman.authenticationapi.exception;

import lombok.*;

@Getter
@Setter
public class AuthenticationServicesException extends  RuntimeException {
    private Integer code;
    private String message;

    public AuthenticationServicesException(Integer code, String message, Throwable cause){
        super(cause);
        this.code= code;
        this.message= message;
    }
    public AuthenticationServicesException(Integer code, String message){
        super();
        this.code= code;
        this.message= message;
    }
    public AuthenticationServicesException(ErrorCode exceptionEnum){
        super();
        this.code= exceptionEnum.getNumber();
        this.message= exceptionEnum.getMessage();
    }
    public AuthenticationServicesException(ErrorCode exceptionEnum, Throwable cause){
        super(cause);
        this.code= exceptionEnum.getNumber();
        this.message= exceptionEnum.getMessage();
    }
}
