package com.example.fitwithme.presentation.controller;

import com.example.fitwithme.application.service.UserService;
import com.example.fitwithme.jwt.JwtUtil;
import com.example.fitwithme.presentation.dto.request.UserRequest;
import com.example.fitwithme.presentation.dto.response.UserResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    private final JwtUtil jwtUtil;

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
    public ResponseEntity<String> signUp(@Valid @RequestBody UserRequest.signUp userRequest){
        String userName = userService.signUp(userRequest);
        return ResponseEntity.ok(userName);
    }

    @PostMapping("/upload-profile")
    public ResponseEntity<String> uploadProfile(@RequestHeader("ACCESS_TOKEN") String accessToken, @RequestPart(value = "image", required = false) MultipartFile image){
        String userId = jwtUtil.getUserIdFromToken(accessToken);
        String profileImage = userService.upload(userId, image);
        return ResponseEntity.ok(profileImage);
    }

}

