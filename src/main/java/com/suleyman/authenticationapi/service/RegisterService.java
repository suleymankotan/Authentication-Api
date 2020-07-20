package com.suleyman.authenticationapi.service;

import com.suleyman.authenticationapi.entity.User;
import com.suleyman.authenticationapi.exception.AuthenticationServicesException;
import com.suleyman.authenticationapi.exception.ErrorCode;
import com.suleyman.authenticationapi.model.request.RegisterRequest;
import com.suleyman.authenticationapi.model.response.BaseResponse;
import com.suleyman.authenticationapi.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class RegisterService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    public BaseResponse register(RegisterRequest registerRequest){
        if (userRepository.getByUsername(registerRequest.getUsername()).isPresent()||userRepository.getByEmail(registerRequest.getEmail()).isPresent())
            throw new AuthenticationServicesException(ErrorCode.GENERAL_EXCEPTION);
        userRepository.save(User.builder()
                .active(1)
                .email(registerRequest.getEmail())
                .name(registerRequest.getName())
                .password(passwordEncoder.encode(registerRequest.getPassword()))
                .role(0)
                .surname(registerRequest.getSurname())
                .username(registerRequest.getUsername())
                .build());
        return BaseResponse.success();

    }
}
