package com.example.ContentSubscription.service;

import com.example.ContentSubscription.Exceptions.NoUserFoundException;
import com.example.ContentSubscription.domain.User;
import com.example.ContentSubscription.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepo userRepo;

    public List<User> getAllUsers() {
        return userRepo.findAll();
    }

    public User getUserById(Long userId) {
        return userRepo.findById(userId).orElseThrow(NoUserFoundException::new);
    }

    public User saveUser(User user) {
        return userRepo.save(user);
    }

    public User loginUser(String email, String password) {
        return userRepo.findByEmailAndPassword(email, password);
    }

}
