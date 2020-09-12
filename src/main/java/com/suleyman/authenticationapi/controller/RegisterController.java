package com.suleyman.authenticationapi.controller;

import com.suleyman.authenticationapi.model.request.RegisterRequest;
import com.suleyman.authenticationapi.model.request.VerificationRequest;
import com.suleyman.authenticationapi.model.response.BaseResponse;
import com.suleyman.authenticationapi.service.RegisterService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/auth")
@Slf4j
public class RegisterController {
    private final RegisterService registerService;

    @PostMapping("/register")
    public BaseResponse register(@RequestBody @Valid RegisterRequest registerRequest){
        return registerService.register(registerRequest);
    }

    @PostMapping("/register/active")
    public BaseResponse registerActive(@RequestBody @Valid VerificationRequest registerRequest){
        return registerService.registerActive(registerRequest);
    }
}
