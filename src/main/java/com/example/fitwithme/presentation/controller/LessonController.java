package com.example.fitwithme.presentation.controller;

import com.example.fitwithme.application.service.LessonService;
import com.example.fitwithme.application.service.UserService;
import com.example.fitwithme.domain.model.Lesson;
import com.example.fitwithme.presentation.dto.request.LessonRequest;
import com.example.fitwithme.presentation.dto.request.UserRequest;
import com.example.fitwithme.presentation.dto.response.UserResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/lessons")
public class LessonController {

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

}