package com.example.fitwithme.presentation.controller;

import com.example.fitwithme.application.service.LessonService;
import com.example.fitwithme.application.service.UserService;
import com.example.fitwithme.common.exception.BadRequestException;
import com.example.fitwithme.common.exception.ErrorStatus;
import com.example.fitwithme.domain.model.Lesson;
import com.example.fitwithme.domain.model.Reserve;
import com.example.fitwithme.jwt.JwtUtil;
import com.example.fitwithme.presentation.dto.request.LessonRequest;
import com.example.fitwithme.presentation.dto.request.UserRequest;
import com.example.fitwithme.presentation.dto.response.LessonResponse;
import com.example.fitwithme.presentation.dto.response.UserResponse;
import com.example.fitwithme.util.DateUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/lessons")
public class LessonController {

    private final JwtUtil jwtUtil;

    private final LessonService lessonService;

    @GetMapping("/search/{selectDate}")
    public ResponseEntity<List<Lesson>> findLessons(@PathVariable String selectDate, @RequestHeader("ACCESS_TOKEN") String accessToken) {
        List<Lesson> lessonList = lessonService.findLessons(selectDate);
        return ResponseEntity.ok(lessonList);
    }

    @GetMapping("/detail")
    public ResponseEntity<Lesson> findLessonDetail(@RequestBody LessonRequest.detail request) {
        Lesson lessonData = lessonService.findLessonDetail(request);
        return ResponseEntity.ok(lessonData);
    }

    @PostMapping("/reserve")
    public ResponseEntity<LessonResponse.reserve> reserve(@Valid @RequestBody LessonRequest.reserve request, @RequestHeader("ACCESS_TOKEN") String accessToken){
        String userId = jwtUtil.getUserIdFromToken(accessToken);
        request.setUserId(userId);

        LessonResponse.reserve reserve = lessonService.reserve(request);
        return ResponseEntity.ok(reserve);
    }

    @PutMapping("/cancel/{reserveId}")
    public ResponseEntity<Integer> cancel(@PathVariable int reserveId){
        boolean isCancelled = lessonService.cancel(reserveId);
        if (isCancelled) {
            return ResponseEntity.ok(reserveId);
        } else {
            throw new BadRequestException(ErrorStatus.CANCEL_FAIL);
        }
    }

    @GetMapping
    public ResponseEntity<List<Reserve>> findReserveLessons(@RequestHeader("ACCESS_TOKEN") String accessToken) {
        String today = DateUtil.getToday();
        String userId = jwtUtil.getUserIdFromToken(accessToken);

        LessonRequest.reserveList reserveList = new LessonRequest.reserveList(today, userId);

        List<Reserve> lessonList = lessonService.findReserveLessons(reserveList);
        return ResponseEntity.ok(lessonList);
    }

}