package com.example.fitwithme.presentation.controller;

import com.example.fitwithme.application.service.SubscriptionService;
import com.example.fitwithme.domain.model.Subscription;
import com.example.fitwithme.jwt.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/subscriptions")
public class SubscriptionController {

    private final JwtUtil jwtUtil;

    private final SubscriptionService subscriptionService;

    @GetMapping("/{centerId}")
    public ResponseEntity<List<Subscription>> findSubscription(@PathVariable Long centerId) {

        List<Subscription> subscriptionList = subscriptionService.findSubscription(centerId);
        return ResponseEntity.ok(subscriptionList);
    }

}