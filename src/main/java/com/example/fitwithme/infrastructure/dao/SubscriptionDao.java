package com.example.fitwithme.infrastructure.dao;

import com.example.fitwithme.common.exception.BadRequestException;
import com.example.fitwithme.common.exception.ErrorStatus;
import com.example.fitwithme.domain.model.Subscription;
import com.example.fitwithme.infrastructure.mapper.SubscriptionMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class SubscriptionDao {
    private final SubscriptionMapper subscriptionMapper;

    public List<Subscription> findSubscription(Long centerId) {
        try {
            return subscriptionMapper.findSubscription(centerId);
        } catch (EmptyResultDataAccessException e) {
            throw new BadRequestException(ErrorStatus.NOT_FOUND_SUBSCRIPTION);
        }
    }
}
