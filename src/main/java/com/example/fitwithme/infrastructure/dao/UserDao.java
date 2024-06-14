package com.example.fitwithme.infrastructure.dao;

import com.example.fitwithme.common.exception.ErrorStatus;
import com.example.fitwithme.common.exception.BadRequestException;
import com.example.fitwithme.domain.model.User;
import com.example.fitwithme.infrastructure.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DuplicateKeyException;
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
            throw new BadRequestException(ErrorStatus.NOT_FOUND_USER);
        }
    }

    public User create(User user) {
        try {
            int result = userMapper.create(user);

            if (result == 0) {
                throw new BadRequestException(ErrorStatus.SIGNUP_FAIL);
            }

            return user;
        } catch (DuplicateKeyException e) {
            throw new BadRequestException(ErrorStatus.DUPLICATE_ID);
        }
    }

    public boolean existsByUserId(String userId) {
        return userMapper.existsByUserId(userId);
    }
}
