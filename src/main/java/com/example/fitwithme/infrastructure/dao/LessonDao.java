package com.example.fitwithme.infrastructure.dao;

import com.example.fitwithme.common.exception.BadRequestException;
import com.example.fitwithme.common.exception.ErrorStatus;
import com.example.fitwithme.domain.model.Lesson;
import com.example.fitwithme.domain.model.User;
import com.example.fitwithme.infrastructure.mapper.LessonMapper;
import com.example.fitwithme.infrastructure.mapper.UserMapper;
import com.example.fitwithme.presentation.dto.request.LessonRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
@RequiredArgsConstructor
public class LessonDao {
    private final LessonMapper lessonMapper;

    public List<Lesson> getLessonList(String selectDate, String day) {
        try {
            return lessonMapper.getLessonList(selectDate, day);
        } catch (EmptyResultDataAccessException e) {
            throw new BadRequestException(ErrorStatus.NOT_FOUND_LESSONLIST);
        }
    }

    public Lesson getLessonData(LessonRequest.detail request) {
        try {
            return lessonMapper.getLessonData(request);
        } catch (EmptyResultDataAccessException e) {
            throw new BadRequestException(ErrorStatus.NOT_FOUND_LESSON);
        }
    }
}
