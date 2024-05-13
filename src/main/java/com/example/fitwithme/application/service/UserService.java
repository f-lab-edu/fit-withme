package com.example.fitwithme.application.service;

import com.example.fitwithme.domain.model.User;
import com.example.fitwithme.infrastructure.mapper.UserMapper;
import com.example.fitwithme.jwt.JwtUtil;
import com.example.fitwithme.presentation.dto.request.UserRequest;
import com.example.fitwithme.presentation.dto.response.UserResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class UserService {
    private final UserMapper userMapper;

    public UserService(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

   @Value("${jwt.secret}")
    private String secretKey;

    private Long expiredMs = 1000 * 60 * 60l;

    public String login(UserRequest userRequest){
        String userId = userRequest.getUserId();
        String userPassword = userRequest.getUserPassword();
        return JwtUtil.createJwt(userId, userPassword, secretKey, expiredMs);
    }
}



