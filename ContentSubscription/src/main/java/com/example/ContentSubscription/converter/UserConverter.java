package com.example.ContentSubscription.converter;

import com.example.ContentSubscription.domain.Fan;
import com.example.ContentSubscription.domain.User;
import com.example.ContentSubscription.dtos.FanDto;
import com.example.ContentSubscription.dtos.UserDTO;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;

@Component
public class UserConverter {
    public UserDTO convertEntityToDto(User entity)
    {
        return UserDTO.builder()
                .userId(entity.getUserId())
                .email(entity.getEmail())
                .password(entity.getPassword())
                .userType(entity.getUserType().toString())
                .photoURL(entity.getPhotoURL())
                .build();
    }

    public User convertDtoToEntity(UserDTO entity)
    {
        return User.builder()
                .userId(entity.getUserId())
                .email(entity.getEmail())
                .password(entity.getPassword())
                .photoURL(entity.getPhotoURL())
                .userType(User.UType.valueOf(entity.getUserType()))
                .build();
    }
    public List<UserDTO> convertEntitiesToDtos(Collection<User> model)
    {
        return model.stream()
                .map(this::convertEntityToDto)
                .toList();
    }
}
