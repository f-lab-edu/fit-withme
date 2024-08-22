package com.example.fitwithme.infrastructure.mapper;

import com.example.fitwithme.domain.model.Subscription;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface SubscriptionMapper {

    List<Subscription> findSubscription(Long centerId);
}
