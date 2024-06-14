package com.example.fitwithme.presentation.controller;

import com.example.fitwithme.application.service.UserService;
import com.example.fitwithme.presentation.dto.request.UserRequest;
import com.example.fitwithme.presentation.dto.response.UserResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @PostMapping("/login")
    public ResponseEntity<UserResponse.tokenInfo> login(@Valid @RequestBody UserRequest.login userRequest) {
        UserResponse.tokenInfo token = userService.login(userRequest);
        return ResponseEntity.ok().body(token);
    }

    @GetMapping("/check-duplicateId/{userId}")
    public ResponseEntity<Boolean> checkDuplicateId(@PathVariable String userId) {
        boolean isAvailable = userService.isUserIdAvailable(userId);
        return ResponseEntity.ok(isAvailable);
    }

    @PostMapping("/signUp")
    public ResponseEntity<String> signIn(@Valid @RequestBody UserRequest.signUp userRequest){
        String userName = userService.signUp(userRequest);
        return ResponseEntity.ok(userName);
    }

}

