package com.example.fitwithme.presentation.dto.request;

import com.example.fitwithme.domain.model.Test;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TestRequest {

    private Long id;

    private String name;

    private int age;

    private String address;

    public Test toDomain() {
        return new Test(id, name, age, address);
    }
}

