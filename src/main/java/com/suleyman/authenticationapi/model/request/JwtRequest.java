package com.suleyman.authenticationapi.model.request;

import lombok.Data;

import java.io.Serializable;

@Data
public class JwtRequest implements Serializable {
    private static final long serialVersionUID= 592646858900515707L;
    private String username;
    private String password;
}
