package com.example.fitwithme.domain.model;

import lombok.Builder;

@Builder
public record Subscription(Long subscriptionId, Long centerId, String centerName, String subscriptionName, int subscriptionPrice, int useMonth) {

}