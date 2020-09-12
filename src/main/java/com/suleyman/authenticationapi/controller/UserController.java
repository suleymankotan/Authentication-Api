package com.suleyman.authenticationapi.controller;

import com.suleyman.authenticationapi.model.response.UserResponse;
import com.suleyman.authenticationapi.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/auth")
@Slf4j
public class UserController {

    private final UserService userService;

    @GetMapping("/user/{userName}")
    private UserResponse getUserDetails(@PathVariable("userName") String username) {
        return userService.getUserDetail(username);
    }
}
