package com.suleyman.authenticationapi.service;

import com.suleyman.authenticationapi.exception.AuthenticationServicesException;
import com.suleyman.authenticationapi.exception.ErrorCode;
import com.suleyman.authenticationapi.model.request.JwtRequest;
import com.suleyman.authenticationapi.model.response.LoginResponse;
import com.suleyman.authenticationapi.model.response.UserResponse;
import com.suleyman.authenticationapi.tokenconfig.JwtTokenUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.time.Instant;
import java.time.ZoneId;

@Slf4j
@Service
@RequiredArgsConstructor
public class LoginService {
    private final JwtTokenUtil jwtTokenUtil;
    private final AuthenticationManager authenticationManager;
    private final UserService userService;

    public LoginResponse login(JwtRequest request, HttpServletRequest httpServletRequest) {
        auth(request.getUsername(),request.getPassword());
        final UserDetails userDetails = userService.loadUserByUsername(request.getUsername());
        final String token = jwtTokenUtil.generateToken(userDetails);
        UserResponse userResponse = userService.getUserDetail(request.getUsername());
        userService.tokenLogger(userResponse,token,httpServletRequest.getRemoteAddr(), Instant.ofEpochMilli(jwtTokenUtil.getExpirationDateFromToken(token).getTime()).atZone(ZoneId.of("Europe/Istanbul")).toLocalDateTime());
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
