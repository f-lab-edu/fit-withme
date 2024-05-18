package com.example.fitwithme.application.service;

import com.example.fitwithme.common.exception.ErrorStatus;
import com.example.fitwithme.common.exception.NotFoundException;
import com.example.fitwithme.domain.model.User;
import com.example.fitwithme.infrastructure.dao.UserDao;
import com.example.fitwithme.jwt.JwtUtil;
import com.example.fitwithme.presentation.dto.request.UserRequest;
import com.example.fitwithme.presentation.dto.response.UserResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static java.rmi.server.LogStream.log;


@Slf4j
@Service
public class UserService {
    private final UserDao userDao;
    private final JwtUtil jwtUtil;

    public UserService(UserDao userDao, JwtUtil jwtUtil) {
        this.userDao = userDao;
        this.jwtUtil = jwtUtil;
    }

    @Transactional
    public UserResponse login(UserRequest.login loginRequest) {
        String userId = loginRequest.getUserId();
        User user = userDao.findById(userId);
        log.info("loginRequest : " + loginRequest.getUserPassword());
        log.info("user : " + user);
        //비밀번호 확인
        if(!loginRequest.getUserPassword().equals(user.userPassword())){
            throw new NotFoundException(ErrorStatus.WRONG_PASSWORD);
        }
        return jwtUtil.generateTokens(user.userId());
    }
}



