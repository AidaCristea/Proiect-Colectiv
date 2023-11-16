package com.example.ContentSubscription.repository;

import com.example.ContentSubscription.domain.Creator;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CreatorRepo extends JpaRepository<Creator, Long> {
}
