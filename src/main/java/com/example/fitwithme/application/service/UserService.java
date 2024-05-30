package com.example.fitwithme.application.service;

import com.example.fitwithme.common.exception.ErrorStatus;
import com.example.fitwithme.common.exception.NotFoundException;
import com.example.fitwithme.domain.model.User;
import com.example.fitwithme.infrastructure.dao.UserDao;
import com.example.fitwithme.jwt.JwtUtil;
import com.example.fitwithme.presentation.dto.request.UserRequest;
import com.example.fitwithme.presentation.dto.response.UserResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static java.rmi.server.LogStream.log;


@Slf4j
@RequiredArgsConstructor
@Service
public class UserService {
    private final UserDao userDao;
    private final JwtUtil jwtUtil;


    @Transactional
    public UserResponse login(UserRequest.login loginRequest) {

        String userId = loginRequest.getUserId();
        User user = userDao.findById(userId);

        if(user == null){
            throw new NotFoundException(ErrorStatus.NOT_FOUND_USER);
        }

        if(!loginRequest.getUserPassword().equals(user.userPassword())){
            throw new NotFoundException(ErrorStatus.WRONG_PASSWORD);
        }
        return jwtUtil.generateTokens(user.userId());
    }
}



