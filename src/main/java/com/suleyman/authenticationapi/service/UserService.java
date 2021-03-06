package com.suleyman.authenticationapi.service;

import com.suleyman.authenticationapi.entity.User;
import com.suleyman.authenticationapi.entity.UserTokenLog;
import com.suleyman.authenticationapi.exception.AuthenticationServicesException;
import com.suleyman.authenticationapi.exception.ErrorCode;
import com.suleyman.authenticationapi.model.response.UserResponse;
import com.suleyman.authenticationapi.model.statuEnum.UserStatusEnum;
import com.suleyman.authenticationapi.repository.UserLogRepository;
import com.suleyman.authenticationapi.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final UserLogRepository userLogRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user =userRepository.getByUsername(username);
        if (!user.isPresent())
            throw new UsernameNotFoundException(String.format("The username doesn't exist",username));
        else if (user.get().getActive().equals(UserStatusEnum.NO_ACTIVE_USER.getCode())){
            throw new AuthenticationServicesException(ErrorCode.USER_NOT_ACTIVE);
        }
        else {
            User users = user.get();
            return new org.springframework.security.core.userdetails.User(users.getUsername(),users.getPassword(),new ArrayList<>());
        }
    }

    public UserResponse getUserDetail(String username) {
        Optional<User> user =userRepository.getByUsername(username);
        if (!user.isPresent())
            throw new UsernameNotFoundException(String.format("The username doesn't exist",username));
        User users = user.get();
        if (user.get().getActive().equals(UserStatusEnum.NO_ACTIVE_USER.getCode()))
            throw new AuthenticationServicesException(ErrorCode.USER_NOT_ACTIVE);
        return UserResponse.builder()
                .active(users.getActive())
                .email(users.getEmail())
                .name(users.getName())
                .surname(users.getSurname())
                .userName(users.getUsername())
                .role(users.getRole())
                .build();
    }


    public void tokenLogger(UserResponse userResponse, String token, String remoteAddr,LocalDateTime expireDate){
       userLogRepository.save(UserTokenLog.builder()
               .user_id(userRepository.getByUsername(userResponse.getUserName()).get().getId())
               .createdDate(LocalDateTime.now(ZoneId.of("Europe/Istanbul")))
               .token(token)
               .Ip(remoteAddr)
               .expireDate(expireDate)
               .build());

    }

}
