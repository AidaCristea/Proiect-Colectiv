package com.example.ContentSubscription.repository;

import com.example.ContentSubscription.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepo extends JpaRepository<Post, Long> {
}
