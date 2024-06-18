package com.example.fitwithme.presentation.dto.request;

import com.example.fitwithme.domain.model.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor
public class LessonRequest {

    @Getter
    @AllArgsConstructor
    public static class detail {

        private String selectDate;

        private String lessonSn;

    }

}
