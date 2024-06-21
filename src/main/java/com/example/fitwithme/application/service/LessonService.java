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
import com.example.fitwithme.presentation.dto.response.UserResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.List;
import java.util.Locale;
import java.util.Map;


@Slf4j
@RequiredArgsConstructor
@Service
public class LessonService {
    private final LessonDao lessonDao;

    public List<Lesson> getLessonList(String selectDate) {
        LocalDate date = LocalDate.parse(selectDate, DateTimeFormatter.ISO_LOCAL_DATE);
        DayOfWeek dayOfWeek = date.getDayOfWeek();
        String day = dayOfWeek.getDisplayName(TextStyle.NARROW, Locale.KOREAN);

        return lessonDao.getLessonList(selectDate, day);
    }

    public Lesson getLessonData(LessonRequest.detail request) {
        return lessonDao.getLessonData(request);
    }

    @Transactional
    public LessonRequest.reserve reserve(LessonRequest.reserve request) {
        Long reserveSn = lessonDao.create(request);

        if(reserveSn > 0){
            request.setReserveSn(reserveSn);
            request.setStatus("예약 성공");
        }else {
            request.setStatus("예약 실패");
        }

        return request;
    }

    @Transactional
    public boolean cancel(int reserveSn) {
        int result = lessonDao.cancel(reserveSn);

        if(result > 0){
            return true;
        }else {
            return false;
        }
    }

    public List<Reserve> getReserveList(LessonRequest.reserveList reserveList) {
        return lessonDao.getReserveList(reserveList);
    }
}