package com.example.fitwithme.infrastructure.dao;

import com.example.fitwithme.common.exception.ErrorStatus;
import com.example.fitwithme.common.exception.WrongRequestException;
import com.example.fitwithme.domain.model.User;
import com.example.fitwithme.infrastructure.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class UserDao {
    private final UserMapper userMapper;

    public User findById(String userId) {
        try {
            return userMapper.findById(userId);
        } catch (EmptyResultDataAccessException e) {
            throw new WrongRequestException(ErrorStatus.NOT_FOUND_USER);
        }
    }

    public User create(User user) {
        int result = userMapper.create(user);

        if(result == 0){
            throw new WrongRequestException(ErrorStatus.SIGNUP_FAIL);
        }

        return userMapper.findById(user.userId());
    }

    public boolean existsByUserId(String userId) {
        return userMapper.existsByUserId(userId) > 0;
    }
}
