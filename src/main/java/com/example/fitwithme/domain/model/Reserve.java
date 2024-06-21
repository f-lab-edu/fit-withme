package com.example.fitwithme.domain.model;

import lombok.Builder;

@Builder
public record Reserve(Long reserveSn, String userId, String reserveDate, String lessonSn, String lessonName, String instructorName, int currPersonnel, int personnel, String lessonDay, String startTime, String endTime) {

}