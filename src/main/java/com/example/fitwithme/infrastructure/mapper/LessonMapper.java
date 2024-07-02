package com.example.fitwithme.infrastructure.mapper;

import com.example.fitwithme.domain.model.Lesson;
import com.example.fitwithme.domain.model.Reserve;
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

    List<Lesson> findLessons(String selectDate, String day);

    Lesson findLessonById(Long lessonId);

    String findCenterByLessonId(Long lessonId);

    String findInstructorByLessonId(Long lessonId);

    int countCurrentPersonnel(LessonRequest.detail request);

    Long create(LessonRequest.reserve request);

    int deleteReserve(int reserveId);

    List<Reserve> findReservesByUserIdAndDate(LessonRequest.reserveList reserveList);

    Lesson findLessonDetailsByLessonId(String lessonId);

    int findCurrentPersonnel(LessonRequest.reserveList reserveList);

    void updateReserveNumberPlus(LessonRequest.reserve request);
}
