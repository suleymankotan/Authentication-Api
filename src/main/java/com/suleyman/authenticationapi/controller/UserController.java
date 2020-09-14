package com.suleyman.authenticationapi.controller;

import com.suleyman.authenticationapi.model.response.UserResponse;
import com.suleyman.authenticationapi.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/user")
public class UserController {

    private final UserService userService;

    @GetMapping("/{userName}")
    private UserResponse getUserDetails(@PathVariable("userName") String username) {
        return userService.getUserDetail(username);
    }
}
