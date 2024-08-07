package com.example.fitwithme.infrastructure.dao;

import com.example.fitwithme.common.exception.BadRequestException;
import com.example.fitwithme.common.exception.ErrorStatus;
import com.example.fitwithme.domain.model.Lesson;
import com.example.fitwithme.domain.model.Reserve;
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

    public List<Lesson> findAllLesson(String selectDate, String day) {
        try {
            return lessonMapper.findAllLesson(selectDate, day);
        } catch (EmptyResultDataAccessException e) {
            throw new BadRequestException(ErrorStatus.NOT_FOUND_LESSONLIST);
        }
    }

    public Lesson findLessonById(Long lessonId) {
        try {
            return lessonMapper.findLessonById(lessonId);
        } catch (EmptyResultDataAccessException e) {
            throw new BadRequestException(ErrorStatus.NOT_FOUND_LESSON);
        }
    }

    public int countCurrentPersonnel(LessonRequest.detail request) {
        try {
            return lessonMapper.countCurrentPersonnel(request);
        } catch (EmptyResultDataAccessException e) {
            throw new BadRequestException(ErrorStatus.NOT_FOUND_LESSON);
        }
    }

    public Long create(LessonRequest.reserve request) {
        Long result = lessonMapper.create(request);

        return result;
    }

    public int deleteReserve(int reserveId) {
        return lessonMapper.cancel(reserveId);
    }

    public List<Reserve> findAllReserveByUserIdAndDate(LessonRequest.reserveList reserveList) {
        try {
            return lessonMapper.findAllReserveByUserIdAndDate(reserveList);
        } catch (EmptyResultDataAccessException e) {
            throw new BadRequestException(ErrorStatus.NOT_FOUND_RESERVEIST);
        }
    }

    public Lesson findLessonDetailsByLessonId(Long lessonId) {
        try {
            return lessonMapper.findLessonDetailsByLessonId(lessonId);
        } catch (EmptyResultDataAccessException e) {
            throw new BadRequestException(ErrorStatus.NOT_FOUND_RESERVEIST);
        }
    }

}
