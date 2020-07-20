package com.suleyman.authenticationapi.service;

import com.suleyman.authenticationapi.entity.User;
import com.suleyman.authenticationapi.model.response.UserResponse;
import com.suleyman.authenticationapi.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class LoginService implements UserDetailsService {

    private final UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user =userRepository.getByUsername(username);
        if (!user.isPresent())
            throw new UsernameNotFoundException(String.format("The username doesn't exist",username));
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
        return UserResponse.builder()
                .active(users.getActive())
                .email(users.getEmail())
                .name(users.getName())
                .surname(users.getSurname())
                .userName(users.getUsername())
                .role(users.getRole())
                .build();
    }
}
