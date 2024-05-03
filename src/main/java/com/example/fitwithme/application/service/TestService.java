package com.example.fitwithme.application.service;

import com.example.fitwithme.domain.model.Test;
import com.example.fitwithme.infrastructure.mapper.TestMapper;
import com.example.fitwithme.presentation.dto.response.TestResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TestService {
    private final TestMapper testMapper;

    public TestService(TestMapper testMapper) {
        this.testMapper = testMapper;
    }

    public TestResponse viewTest(Long id) {
        Test test = testMapper.find(id);
        return TestResponse.from(test);
    }

    @Transactional
    public TestResponse createPerson(Test test) {
        int id = testMapper.create(test);
        Test foundPerson = testMapper.find((long) id);
        return TestResponse.from(foundPerson);
    }
}


