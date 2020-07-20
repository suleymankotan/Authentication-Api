package com.suleyman.authenticationapi.exception;

import com.suleyman.authenticationapi.exception.ErrorCode;
import lombok.*;

@Getter
@Setter
public class AuthenticationServiceException extends  RuntimeException {
    private Integer code;
    private String message;

    public AuthenticationServiceException(Integer code,String message,Throwable cause){
        super(cause);
        this.code= code;
        this.message= message;
    }
    public AuthenticationServiceException(Integer code,String message){
        super();
        this.code= code;
        this.message= message;
    }
    public AuthenticationServiceException(ErrorCode exceptionEnum){
        super();
        this.code= exceptionEnum.getNumber();
        this.message= exceptionEnum.getMessage();
    }
    public AuthenticationServiceException(ErrorCode exceptionEnum, Throwable cause){
        super(cause);
        this.code= exceptionEnum.getNumber();
        this.message= exceptionEnum.getMessage();
    }
}
