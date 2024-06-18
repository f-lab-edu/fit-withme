package com.example.fitwithme.domain.model;

import lombok.Builder;

@Builder
public record Lesson(Long lessonSn, String center, String lessonName, String instructorName, int currPersonnel, int personnel, String lessonDay, String startTime, String endTime) {

}