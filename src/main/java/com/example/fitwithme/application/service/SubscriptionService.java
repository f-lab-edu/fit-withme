package com.example.fitwithme.application.service;

import com.example.fitwithme.domain.model.Subscription;
import com.example.fitwithme.infrastructure.dao.SubscriptionDao;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;


@Slf4j
@RequiredArgsConstructor
@Service
public class SubscriptionService {
    private final SubscriptionDao subscriptionDao;

    public List<Subscription> findSubscription(Long centerId) {
        List<Subscription> subscriptions = subscriptionDao.findSubscription(centerId);

        return subscriptions;
    }
}