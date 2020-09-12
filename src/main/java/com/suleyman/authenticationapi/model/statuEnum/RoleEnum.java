package com.suleyman.authenticationapi.model.statuEnum;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum  RoleEnum {
    USER(1),
    ADMIN_USER(10);


    private Integer code;
}
