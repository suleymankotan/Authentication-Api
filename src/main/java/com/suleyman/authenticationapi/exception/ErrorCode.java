package com.suleyman.authenticationapi.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    GENERAL_EXCEPTION(4000,"Sistemde bir hata oluştu."),
    REQUIRED_PARAMETERS_CANNOT_BE_NULL(4001,"Zorunlu parametreler boş bırakılamaz."),
    NO_RECORDS_FOUND(4002,"Kayıt Bulunamadı.");
    private int number;
    private String message;
}
