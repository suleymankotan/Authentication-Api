package com.suleyman.authenticationapi.service;

import com.suleyman.authenticationapi.entity.MailSend;
import com.suleyman.authenticationapi.entity.User;
import com.suleyman.authenticationapi.entity.VerificationCode;
import com.suleyman.authenticationapi.exception.AuthenticationServicesException;
import com.suleyman.authenticationapi.exception.ErrorCode;
import com.suleyman.authenticationapi.model.request.RegisterRequest;
import com.suleyman.authenticationapi.model.request.VerificationRequest;
import com.suleyman.authenticationapi.model.response.BaseResponse;
import com.suleyman.authenticationapi.model.statuEnum.CodeEnum;
import com.suleyman.authenticationapi.model.statuEnum.RoleEnum;
import com.suleyman.authenticationapi.model.statuEnum.UserStatusEnum;
import com.suleyman.authenticationapi.repository.MailSendRepository;
import com.suleyman.authenticationapi.repository.UserRepository;
import com.suleyman.authenticationapi.repository.VerificationCodeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import java.time.LocalDateTime;
import java.time.ZoneId;

@Slf4j
@Service
@RequiredArgsConstructor
public class RegisterService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final VerificationCodeRepository verificationCodeRepository;
    private final MailSendRepository mailSendRepository;
    String code = null;

    public BaseResponse register(RegisterRequest registerRequest){
        if (userRepository.getByUsername(registerRequest.getUsername()).isPresent()||userRepository.getByEmail(registerRequest.getEmail()).isPresent())
            throw new AuthenticationServicesException(ErrorCode.GENERAL_EXCEPTION);

        User users = userRepository.save(User.builder()
                .active(UserStatusEnum.NO_ACTIVE_USER.getCode())
                .email(registerRequest.getEmail())
                .name(registerRequest.getName())
                .password(passwordEncoder.encode(registerRequest.getPassword()))
                .role(RoleEnum.USER.getCode())
                .surname(registerRequest.getSurname())
                .username(registerRequest.getUsername())
                .build());

        while (true){
            code=String.valueOf((int)(Math.random() * 7 * 212* 10 ) /2);
            VerificationCode verificationCode = verificationCodeRepository.getByCode(code);
            if (verificationCode == null)
                break;
        }

        verificationCodeRepository.save(VerificationCode.builder()
                .createdDate(LocalDateTime.now(ZoneId.of("Europe/Istanbul")))
                .expireDate(LocalDateTime.now(ZoneId.of("Europe/Istanbul")))
                .userId(users.getId())
                .code(code)
                .active(CodeEnum.NOT_SUCCESS.getCode())
                .build());

        mailSendRepository.save(MailSend.builder()
                .mailKey("NO-REPLY")
                .status(CodeEnum.NOT_SUCCESS.getCode())
                .subject("Active Code")
                .createdDate(LocalDateTime.now(ZoneId.of("Europe/Istanbul")))
                .To_C(users.getEmail())
                .text("<p>Code:</p>"+ code)
                .build());

        return BaseResponse.success();
    }

    public BaseResponse registerActive(VerificationRequest registerRequest) {
        VerificationCode verificationCode= verificationCodeRepository.getByCode(registerRequest.getCode());
        if (verificationCode == null)
            throw new AuthenticationServicesException(ErrorCode.NO_RECORDS_FOUND);


        User user=userRepository.getById(verificationCode.getUserId());
        user.setActive(UserStatusEnum.ACTIVE_USER.getCode());
        userRepository.save(user);
        verificationCode.setActive(CodeEnum.SUCCESS.getCode());
        verificationCodeRepository.save(verificationCode);


        return BaseResponse.success();
    }
}
