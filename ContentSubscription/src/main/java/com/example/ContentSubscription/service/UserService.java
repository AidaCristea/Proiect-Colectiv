package com.example.ContentSubscription.service;

import com.example.ContentSubscription.Exceptions.NoUserFoundException;
import com.example.ContentSubscription.domain.User;
import com.example.ContentSubscription.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepo userRepo;

    public List<User> getAllUsers(){
        return userRepo.findAll();
    }

    public User getUserById(Long userId){
        return userRepo.findById(userId).orElseThrow(NoUserFoundException::new);
    }

    public User saveUser(User user){
        return userRepo.save(user);
    }

    public User loginUser(String email, String password) {

        try
        {
            User foundUser = userRepo.findByEmailAndPassword(email,password);
            return foundUser;

        }
        catch (NullPointerException ex)
        {
            return null;
        }


    }

}
