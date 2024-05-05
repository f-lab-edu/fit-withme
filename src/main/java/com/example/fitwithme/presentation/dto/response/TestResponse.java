package com.example.fitwithme.presentation.dto.response;

import com.example.fitwithme.domain.model.Test;

public record TestResponse(Long id, String name, int age, String address) {

    public static TestResponse from(Test test) {
        return new TestResponse(test.id(), test.name(), test.age(), test.address());
    }
}

