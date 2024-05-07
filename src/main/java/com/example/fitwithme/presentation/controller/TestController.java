package com.example.fitwithme.presentation.controller;

import com.example.fitwithme.application.service.TestService;
import com.example.fitwithme.presentation.dto.request.TestRequest;
import com.example.fitwithme.presentation.dto.response.TestResponse;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/tests")
public class TestController {

    private final TestService testService;

    public TestController(TestService testService) {
        this.testService = testService;
    }

    @GetMapping("/{id}")
    public TestResponse viewTest(@PathVariable("id") Long id) {
        return testService.viewTest(id);
    }

    @PostMapping
    public TestResponse registerTest(@RequestBody TestRequest testRequest) {
        return testService.createPerson(testRequest.toDomain());
    }
}

