package com.example.fitwithme.infrastructure.dao;

import com.example.fitwithme.domain.model.User;
import com.example.fitwithme.infrastructure.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.QueryTimeoutException;
import org.springframework.stereotype.Repository;

@Repository
public class UserDao {
    private final UserMapper userMapper;

    @Autowired
    public UserDao(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    public User findById(String loginId) {
        try {
            return userMapper.findById(loginId);
        } catch (EmptyResultDataAccessException e) {
            throw new BadRequestException(NOT_FOUND_USER);
        }
    }
}
