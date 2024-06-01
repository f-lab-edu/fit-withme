package com.example.fitwithme.infrastructure.mapper;

import com.example.fitwithme.domain.model.User;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import org.apache.ibatis.annotations.Mapper;

@Mapper
@Repository
public interface UserMapper {

    @Select("SELECT * FROM users WHERE user_id = #{id}")
    User findById(String id);

}
