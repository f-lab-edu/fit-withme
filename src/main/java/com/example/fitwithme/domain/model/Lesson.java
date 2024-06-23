package com.example.fitwithme.domain.model;

import lombok.Builder;

@Builder
public record Lesson(Long lessonId, String center, String lessonName, String instructorName, int currentPersonnel, int personnel, String lessonDay, String startTime, String endTime) {

}