package com.suleyman.authenticationapi.controller;

import com.suleyman.authenticationapi.model.request.JwtRequest;
import com.suleyman.authenticationapi.model.response.LoginResponse;
import com.suleyman.authenticationapi.service.LoginService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;


@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/auth")
@Slf4j
public class LoginController {

    private final LoginService loginService;

    @PostMapping("/login")
    public LoginResponse authenticationToken(@RequestBody @Valid JwtRequest request, HttpServletRequest httpServletRequest){
        return loginService.login(request,httpServletRequest);
    }
}
