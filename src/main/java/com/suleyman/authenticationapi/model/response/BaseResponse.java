package com.suleyman.authenticationapi.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BaseResponse {
    private int code;
    private String message;

    public static BaseResponse success(){
        return BaseResponse.builder().code(200).message("İşlem Başarılı.").build();
    }

    public static BaseResponse successReject(){
        return BaseResponse.builder().code(400).message("İşlem Reddedilmiştir..").build();
    }
}
