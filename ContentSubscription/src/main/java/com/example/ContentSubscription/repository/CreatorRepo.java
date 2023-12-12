package com.example.ContentSubscription.repository;

import com.example.ContentSubscription.domain.Creator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CreatorRepo extends JpaRepository<Creator, Long> {


}
