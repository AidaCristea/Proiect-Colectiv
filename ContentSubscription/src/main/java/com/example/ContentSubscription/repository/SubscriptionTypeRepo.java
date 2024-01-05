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


    @Query("SELECT CASE WHEN :type = 'LITE' THEN C.priceLite " +
            "WHEN :type = 'PRO' THEN C.pricePro " +
            "WHEN :type = 'ULTIMATE' THEN C.priceUltimate " +
            "ELSE 0 END AS selected_price " +
            "FROM Creator C WHERE C.creatorId = :creatorId")
    Long price(@Param("creatorId") Long creatorId, @Param("type")String type);
}
