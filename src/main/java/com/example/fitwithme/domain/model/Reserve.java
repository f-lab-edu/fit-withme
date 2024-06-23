package com.example.fitwithme.domain.model;

import lombok.Builder;

@Builder
public record Reserve(Long reserveId, String userId, String reserveDate, String lessonId, String lessonName, String instructorName, int currentPersonnel, int personnel, String lessonDay, String startTime, String endTime) {

}