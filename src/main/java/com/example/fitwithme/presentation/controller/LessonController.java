package com.example.fitwithme.presentation.controller;

import com.example.fitwithme.application.service.LessonService;
import com.example.fitwithme.application.service.UserService;
import com.example.fitwithme.domain.model.Lesson;
import com.example.fitwithme.domain.model.Reserve;
import com.example.fitwithme.jwt.JwtUtil;
import com.example.fitwithme.presentation.dto.request.LessonRequest;
import com.example.fitwithme.presentation.dto.request.UserRequest;
import com.example.fitwithme.presentation.dto.response.UserResponse;
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

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private final LessonService lessonService;

    @GetMapping("/search/{selectDate}")
    public ResponseEntity<List<Lesson>> getLessonList(@PathVariable String selectDate) {
        List<Lesson> lessonList = lessonService.getLessonList(selectDate);
        return ResponseEntity.ok(lessonList);
    }

    @GetMapping("/detail")
    public ResponseEntity<Lesson> getLessonList(@RequestBody LessonRequest.detail request) {
        Lesson lessonData = lessonService.getLessonData(request);
        return ResponseEntity.ok(lessonData);
    }

    @PostMapping("/reserve")
    public ResponseEntity<LessonRequest.reserve> reserve(@Valid @RequestBody LessonRequest.reserve request, @RequestHeader("ACCESS_TOKEN") String accessToken){
        String userId = jwtUtil.getUserIdFromToken(accessToken);
        request.setUserId(userId);

        LessonRequest.reserve reserve = lessonService.reserve(request);
        return ResponseEntity.ok(reserve);
    }

    @PutMapping("/cancel/{reserveSn}")
    public ResponseEntity<String> cancel(@PathVariable int reserveSn){
        boolean isCancelled = lessonService.cancel(reserveSn);
        if (isCancelled) {
            return ResponseEntity.ok("예약 취소");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("예약 취소 실패");
        }
    }

    @GetMapping
    public ResponseEntity<List<Reserve>> getReserveList(@RequestHeader("ACCESS_TOKEN") String accessToken) {
        LessonRequest.reserveList reserveList = new LessonRequest.reserveList();

        LocalDate today = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String date = today.format(formatter);
        reserveList.setToday(date);

        String userId = jwtUtil.getUserIdFromToken(accessToken);
        reserveList.setUserId(userId);

        List<Reserve> lessonList = lessonService.getReserveList(reserveList);
        return ResponseEntity.ok(lessonList);
    }

}