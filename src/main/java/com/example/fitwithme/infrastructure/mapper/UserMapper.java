package com.example.fitwithme.infrastructure.mapper;

import com.example.fitwithme.domain.model.User;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;
import org.apache.ibatis.annotations.Mapper;

@Mapper
@Repository
public interface UserMapper {

    @Select("SELECT * FROM users WHERE user_id = #{userId} and leaved = 0")
    User findById(String userId);

    int create(User user);

    @Select("SELECT EXISTS(SELECT 1 FROM users WHERE user_id = #{userId} and leaved = 0)")
    boolean existsByUserId(String userId);

    @Update("UPDATE users SET image_url = #{profileImage} WHERE user_id = #{userId}")
    void uploadProfile(String userId, String profileImage);

    @Update("UPDATE USER SET LEAVED=1 WHERE USER_ID=#{userId}")
    int deleteUser(int userId);
}
