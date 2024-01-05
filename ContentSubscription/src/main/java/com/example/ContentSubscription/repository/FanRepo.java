package com.example.ContentSubscription.repository;

import com.example.ContentSubscription.domain.Fan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FanRepo extends JpaRepository<Fan, Long> {
}
