package com.example.fitwithme.application.service;

import com.example.fitwithme.domain.model.User;
import com.example.fitwithme.infrastructure.dao.UserDao;
import com.example.fitwithme.infrastructure.mapper.UserMapper;
import com.example.fitwithme.jwt.JwtUtil;
import com.example.fitwithme.presentation.dto.request.UserRequest;
import com.example.fitwithme.presentation.dto.response.UserResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class UserService {
    private final UserDao userDao;
    private final JwtUtil jwtUtil;

    public UserService(UserDao userDao, JwtUtil jwtUtil) {
        this.userDao = userDao;
        this.jwtUtil = jwtUtil;
    }

    @Transactional
    public String login(UserRequest.login loginRequest) {
        String userId = loginRequest.getUserId();
        User user = userDao.findById(loginRequest.getUserId());
        //비밀번호 확인 등의 유효성 검사 진행
        return jwtUtil.createToken(user.userId());
    }
}



