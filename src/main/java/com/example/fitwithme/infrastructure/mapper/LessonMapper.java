package com.example.fitwithme.infrastructure.mapper;

import com.example.fitwithme.domain.model.Lesson;
import com.example.fitwithme.domain.model.User;
import com.example.fitwithme.presentation.dto.request.LessonRequest;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface LessonMapper {

    @Select("SELECT * FROM users WHERE user_id = #{userId} and delete_at = 'N'")
    User findById(String userId);

    int create(User user);

    @Select("SELECT EXISTS(SELECT 1 FROM users WHERE user_id = #{userId})")
    boolean existsByUserId(String userId);

    List<Lesson> getLessonList(String selectDate, String day);

    Lesson getLessonData(LessonRequest.detail request);
}
