package com.example.fitwithme.presentation.dto.response;

import com.example.fitwithme.common.enums.ReservationStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


public class LessonResponse {

    @Getter
    @Setter
    @Builder
    public static class reserve {

        private Long reserveId;
        private ReservationStatus status;

    }

}
