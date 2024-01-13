package com.example.ContentSubscription.repository;

import com.example.ContentSubscription.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepo extends JpaRepository<User, Long> {
    public User findByEmailAndPassword(String email, String password);
}
