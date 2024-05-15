package com.example.fitwithme.presentation.controller;

import com.example.fitwithme.application.service.UserService;
import com.example.fitwithme.presentation.dto.request.UserRequest;
import com.example.fitwithme.presentation.dto.response.UserResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<UserResponse> login(@RequestBody UserRequest.login userRequest) {
        String token = userService.login(userRequest);
        return ResponseEntity.ok().body(new UserResponse(token, "bearer"));
    }
}

