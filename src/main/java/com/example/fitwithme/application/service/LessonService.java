package com.example.fitwithme.application.service;

import com.example.fitwithme.common.exception.BadRequestException;
import com.example.fitwithme.common.exception.ErrorStatus;
import com.example.fitwithme.domain.model.Lesson;
import com.example.fitwithme.domain.model.Reserve;
import com.example.fitwithme.domain.model.User;
import com.example.fitwithme.infrastructure.dao.LessonDao;
import com.example.fitwithme.infrastructure.dao.UserDao;
import com.example.fitwithme.jwt.JwtUtil;
import com.example.fitwithme.presentation.dto.request.LessonRequest;
import com.example.fitwithme.presentation.dto.request.UserRequest;
import com.example.fitwithme.presentation.dto.response.LessonResponse;
import com.example.fitwithme.presentation.dto.response.UserResponse;
import com.example.fitwithme.util.DateUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;


@Slf4j
@RequiredArgsConstructor
@Service
public class LessonService {
    private final LessonDao lessonDao;


    public List<Lesson> findLessons(String selectDate) {
        String day = DateUtil.getDayByDate(selectDate);

        return lessonDao.findLessons(selectDate, day);
    }

    public Lesson findLessonDetail(LessonRequest.detail request) {

        Long lessonId = request.getLessonId();
        String selectDate = request.getSelectDate();

        Lesson lessonBase = lessonDao.findLessonById(lessonId);
        String center = lessonDao.findCenterByLessonId(lessonId);
        String instructorName = lessonDao.findInstructorByLessonId(lessonId);

        Map<String, Object> params = new HashMap<>();
        params.put("lessonId", lessonId);
        params.put("selectDate", selectDate);
        int currentPersonnel = lessonDao.countCurrentPersonnel(params);

        return Lesson.builder()
                .lessonId(lessonBase.lessonId())
                .center(center)
                .lessonName(lessonBase.lessonName())
                .instructorName(instructorName)
                .currentPersonnel(currentPersonnel)
                .personnel(lessonBase.personnel())
                .lessonDay(lessonBase.lessonDay())
                .startTime(lessonBase.startTime())
                .endTime(lessonBase.endTime())
                .build();
    }

    @Transactional
    public LessonResponse.reserve reserve(LessonRequest.reserve request) {
        Long reserveId = lessonDao.create(request);
        LessonResponse.reserve response = LessonResponse.reserve.builder().build();

        if(reserveId > 0){
            response.setReserveId(reserveId);
            response.setStatus("예약 성공");
        }else {
            response.setStatus("예약 실패");
        }

        return response;
    }

    @Transactional
    public boolean cancel(int reserveId) {
        int result = lessonDao.updateReserve(reserveId);

        if(result > 0){
            return true;
        }else {
            return false;
        }
    }

    public List<Reserve> findReserveLessons(LessonRequest.reserveList reserveList) {
        return lessonDao.findReserveLessons(reserveList);
    }
}