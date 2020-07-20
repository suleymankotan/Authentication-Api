package com.suleyman.authenticationapi.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginResponse {
    private String name;

    private String surname;

    private String userName;

    private String token;

    private Integer active;

    private Integer role;

    private String email;
}
