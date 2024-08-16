package com.example.fitwithme.infrastructure.mapper;

import com.example.fitwithme.domain.model.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserMapper {

    @Select("SELECT * FROM users WHERE user_id = #{userId} and leaved = 0")
//    @Results({@Result(property = "imageUrl", column = "image_url")})
    @ConstructorArgs({@Arg(column = "id", javaType = Long.class),
            @Arg(column = "user_id", javaType = String.class),
            @Arg(column = "user_name", javaType = String.class),
            @Arg(column = "user_password", javaType = String.class),
            @Arg(column = "email", javaType = String.class),
            @Arg(column = "phone", javaType = String.class),
            @Arg(column = "image_url", javaType = String.class)})
    User findById(String userId);

    int create(User user);

    @Select("SELECT EXISTS(SELECT 1 FROM users WHERE user_id = #{userId} and leaved = 0)")
    boolean existsByUserId(String userId);

    @Update("UPDATE users SET image_url = #{profileImage} WHERE user_id = #{userId}")
    void uploadProfile(String userId, String profileImage);

    @Update("UPDATE users SET leaved=1 WHERE user_id=#{userId}")
    int deleteUser(String userId);

    int update(User user);

}
