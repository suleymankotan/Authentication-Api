package com.suleyman.authenticationapi.model.statuEnum;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum UserStatusEnum {
    NO_ACTIVE_USER(1),
    ACTIVE_USER(2),
    CHANGE_PASSWORD(3),
    BLOCKED(4);

    private Integer code;
}
