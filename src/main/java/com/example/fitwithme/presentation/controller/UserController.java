package com.example.fitwithme.presentation.controller;

import com.example.fitwithme.application.service.UserService;
import com.example.fitwithme.common.exception.BadRequestException;
import com.example.fitwithme.common.exception.ErrorStatus;
import com.example.fitwithme.domain.model.Lesson;
import com.example.fitwithme.domain.model.User;
import com.example.fitwithme.jwt.JwtUtil;
import com.example.fitwithme.presentation.dto.request.LessonRequest;
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

    @PutMapping("/update-profile")
    public ResponseEntity<String> updateProfile(@RequestHeader("ACCESS_TOKEN") String accessToken, @RequestPart(value = "image", required = false) MultipartFile image){
        String userId = jwtUtil.getUserIdFromToken(accessToken);
        String profileImage = userService.updateProfile(userId, image);
        return ResponseEntity.ok(profileImage);
    }

    @PutMapping("/leave/{userId}")
    public ResponseEntity<String> leave(@PathVariable String userId){
        boolean isLeaved = userService.leave(userId);
        if (isLeaved) {
            return ResponseEntity.ok(userId);
        } else {
            throw new BadRequestException(ErrorStatus.LEAVE_FAIL);
        }
    }

    @GetMapping("/{userId}")
    public ResponseEntity<User> findUserDetail(@PathVariable String userId) {
        User user = userService.findUserDetail(userId);
        return ResponseEntity.ok(user);
    }

    @PutMapping("/update-userInfo")
    public ResponseEntity<String> updateUserInfo(@Valid @RequestBody UserRequest.update userRequest){
        String userName = userService.updateUserInfo(userRequest);
        return ResponseEntity.ok(userName);
    }

}
