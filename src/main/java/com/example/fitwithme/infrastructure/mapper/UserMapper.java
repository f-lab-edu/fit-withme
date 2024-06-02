package com.example.fitwithme.infrastructure.mapper;

import com.example.fitwithme.domain.model.User;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import org.apache.ibatis.annotations.Mapper;

@Mapper
@Repository
public interface UserMapper {

    @Select("SELECT * FROM users WHERE user_id = #{userId} and delete_at = 'N'")
    User findById(String userId);

    int create(User user);

    @Select("SELECT COUNT(*) FROM users WHERE user_id = #{userId} and delete_at = 'N'")
    int existsByUserId(String userId);
}
