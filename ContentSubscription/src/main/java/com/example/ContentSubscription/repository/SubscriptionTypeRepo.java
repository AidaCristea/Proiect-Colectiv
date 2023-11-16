package com.example.ContentSubscription.repository;

import com.example.ContentSubscription.domain.SubscriptionType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubscriptionTypeRepo extends JpaRepository<SubscriptionType, Long> {
}
