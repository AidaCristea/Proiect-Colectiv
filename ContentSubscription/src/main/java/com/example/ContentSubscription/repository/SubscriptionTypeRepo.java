package com.example.ContentSubscription.repository;

import com.example.ContentSubscription.domain.SubscriptionType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SubscriptionTypeRepo extends JpaRepository<SubscriptionType, Long> {
    @Query("SELECT S.subscriptionTypeId FROM SubscriptionType S WHERE S.fan.fanId = :fanId")
    List<Long> findByFanId(@Param("fanId") Long fanId);

    @Query("SELECT S.creator.creatorId FROM SubscriptionType S WHERE S.subscriptionTypeId = :subscriptionTypeId")
    List<Long> findCreatorBySubscriptionId(@Param("subscriptionTypeId") Long subscriptionTypeId);

}
