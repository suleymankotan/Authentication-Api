package com.suleyman.authenticationapi.controller;

import com.suleyman.authenticationapi.exception.AuthenticationServicesException;
import com.suleyman.authenticationapi.exception.ErrorCode;
import com.suleyman.authenticationapi.model.request.JwtRequest;
import com.suleyman.authenticationapi.model.request.RegisterRequest;
import com.suleyman.authenticationapi.model.request.VerificationRequest;
import com.suleyman.authenticationapi.model.response.BaseResponse;
import com.suleyman.authenticationapi.model.response.LoginResponse;
import com.suleyman.authenticationapi.model.response.UserResponse;
import com.suleyman.authenticationapi.service.LoginService;
import com.suleyman.authenticationapi.service.RegisterService;
import com.suleyman.authenticationapi.tokenconfig.JwtTokenUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.*;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/auth")
public class LoginController {
    private final LoginService loginService;
    private final AuthenticationManager authenticationManager;
    private final RegisterService registerService;

    private final JwtTokenUtil jwtTokenUtil;


    @PostMapping("/login")
    public LoginResponse authenticationToken(@RequestBody @Valid JwtRequest request){
        return login(request);
    }

    @PostMapping("/register")
    public BaseResponse register(@RequestBody @Valid RegisterRequest registerRequest){
        return registerService.register(registerRequest);
    }

    @PostMapping("/register/active")
    public BaseResponse registerActive(@RequestBody @Valid VerificationRequest registerRequest){
        return registerService.registerActive(registerRequest);
    }

    @GetMapping("/user/{userName}")
    private UserResponse getUserDetails(@PathVariable("userName") String username) {
        return loginService.getUserDetail(username);
    }

    private LoginResponse login(JwtRequest request) {
        auth(request.getUsername(),request.getPassword());
        final UserDetails userDetails = loginService.loadUserByUsername(request.getUsername());
        final String token = jwtTokenUtil.generateToken(userDetails);
        UserResponse userResponse = getUserDetails(request.getUsername());
        return LoginResponse.builder().user(userResponse).token(token).build();
    }

    private void auth(String username, String password) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username,password));
        }catch (DisabledException | BadCredentialsException exception){
            throw new AuthenticationServicesException(ErrorCode.USER_OR_PASSWORD_NOT_CORRECT);
        }
    }
}
