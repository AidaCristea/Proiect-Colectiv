package com.example.ContentSubscription.converter;

import com.example.ContentSubscription.domain.User;
import com.example.ContentSubscription.dtos.UserDTO;
import com.example.ContentSubscription.dtos.UserDTOShort;

import java.util.Collection;
import java.util.List;

public class UserShortConverter {

    public UserDTOShort convertEntityToDto(User entity)
    {
        return UserDTOShort.builder()
                .email(entity.getEmail())
                .password(entity.getPassword())
                .build();
    }

    public User convertDtoToEntity(UserDTOShort entity)
    {
        return User.builder()
                .email(entity.getEmail())
                .password(entity.getPassword())
                .build();
    }
    public List<UserDTOShort> convertEntitiesToDtos(Collection<User> model)
    {
        return model.stream()
                .map(this::convertEntityToDto)
                .toList();
    }
}
