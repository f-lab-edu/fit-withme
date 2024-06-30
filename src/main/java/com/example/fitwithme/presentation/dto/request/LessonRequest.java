package com.example.fitwithme.presentation.dto.request;

import com.example.fitwithme.domain.model.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@NoArgsConstructor
public class LessonRequest {

    @Getter
    @AllArgsConstructor
    public static class detail {

        private String selectDate;
        private Long lessonId;

    }

    @Getter
    @Setter
    @AllArgsConstructor
    public static class reserve {

        private Long lessonId;
        private String userId;
        private String selectDate;
        private Long reserveId;
        private String status;

    }

    @Getter
    @AllArgsConstructor
    public static class reserveList {

        private String today;
        private String userId;

    }

}
