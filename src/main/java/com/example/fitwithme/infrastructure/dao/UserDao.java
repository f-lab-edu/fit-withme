package com.example.fitwithme.infrastructure.dao;

import com.example.fitwithme.common.exception.ErrorStatus;
import com.example.fitwithme.common.exception.NotFoundException;
import com.example.fitwithme.domain.model.User;
import com.example.fitwithme.infrastructure.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.QueryTimeoutException;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class UserDao {
    private final UserMapper userMapper;

    public User findById(String userId) {
        try {
            return userMapper.findById(userId);
        } catch (EmptyResultDataAccessException e) {
            throw new NotFoundException(ErrorStatus.NOT_FOUND_USER);
        }
    }
}
