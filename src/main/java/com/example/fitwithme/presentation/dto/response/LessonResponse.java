package com.example.fitwithme.presentation.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


public class LessonResponse {

    @Getter
    @Setter
    @Builder
    public static class reserve {

        private Long reserveId;
        private String status;

    }

}
