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
    public ResponseEntity<UserResponse> login(@Valid @RequestBody UserRequest.login userRequest) {
        UserResponse token = userService.login(userRequest);
        return ResponseEntity.ok().body(token);
    }

    @GetMapping("/check-duplicateId/{userId}")
    public ResponseEntity<Boolean> checkDuplicateId(@PathVariable String userId) {
        boolean isAvailable = userService.isUserIdAvailable(userId);
        return ResponseEntity.ok(isAvailable);
    }

    @PostMapping("/signUp")
    public ResponseEntity<String> signIn(@Valid @RequestBody UserRequest.signUp userRequest){
        if (!userService.isUserIdAvailable(userRequest.getUserId())) {
            return ResponseEntity.badRequest().body("아이디 중복 확인을 완료해주세요.");
        }

        String userName = userService.signUp(userRequest);
        return ResponseEntity.ok(userName + "님 가입을 축하드립니다!");
    }

}

