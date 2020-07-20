package com.suleyman.authenticationapi.controller;

import com.suleyman.authenticationapi.service.LoginService;
import com.suleyman.authenticationapi.service.RegisterService;
import com.suleyman.authenticationapi.tokenconfig.JwtTokenUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/auth")
public class LoginController {
    private final LoginService loginService;
    private final RegisterService registerService;

    private final JwtTokenUtil jwtTokenUtil;

    @GetMapping("/hello")
    public String hello(){
        return "hello word";
    }
}
