package com.example.ContentSubscription;


import com.example.ContentSubscription.controller.UserController;
import com.example.ContentSubscription.converter.UserConverter;
import com.example.ContentSubscription.domain.User;
import com.example.ContentSubscription.dtos.UserDTO;
import com.example.ContentSubscription.dtos.UserDTOShort;
import com.example.ContentSubscription.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UsersTests {

    private List<User> users = new ArrayList<>();

    @Mock
    private UserConverter userConverter;

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    @Test
    public void testAddUser() {
        User userToAdd = User.builder()
                .userId(1L)
                .email("test@example.com")
                .password("password123")
                .userType(User.UType.FAN)
                .photoURL("url")
                .build();

        User savedUser = User.builder()
                .userId(1L)
                .email("test@example.com")
                .password("password123")
                .userType(User.UType.FAN)
                .photoURL("url")
                .build();

        UserDTO userDtoToAdd = UserDTO.builder()
                .email("test@example.com")
                .password("password123")
                .userType("FAN")
                .photoURL("url")
                .build();

        users.add(savedUser);

        when(userService.saveUser(userToAdd)).thenReturn(savedUser);
        when(userConverter.convertDtoToEntity(userDtoToAdd)).thenReturn(userToAdd);

        ResponseEntity<UserDTO> response = userController.addUser(userDtoToAdd);
        verify(userConverter, Mockito.times(1)).convertDtoToEntity(userDtoToAdd);
        verify(userService, Mockito.times(1)).saveUser(userToAdd);

        assertAll(
                () -> assertEquals(HttpStatus.CREATED, response.getStatusCode(),
                        "Actual status code and expected code are not the same!")
        );
    }

    @Test
    void testLoginUser() {
        // UserDTOShort for login request
        UserDTOShort userDTOShort = UserDTOShort.builder()
                .email("test@example.com")
                .password("password123")
                .build();

        // Normal User to be returned by the mocked service
        User expectedUser = User.builder()
                .userId(1L)
                .email("test@example.com")
                .password("password123")
                .userType(User.UType.FAN)
                .photoURL("url")
                .build();

        // Mock the login method and return the user
        when(userService.loginUser("test@example.com", "password123")).thenReturn(expectedUser);

        // Mock the conversion method and return a non-null UserDTO
        when(userConverter.convertEntityToDto(expectedUser)).thenReturn(UserDTO.builder()
                .userId(expectedUser.getUserId())
                .email(expectedUser.getEmail())
                .password(expectedUser.getPassword())
                .userType(expectedUser.getUserType().toString())
                .photoURL(expectedUser.getPhotoURL())
                .build());

        // Call the login method from UserController
        ResponseEntity<UserDTO> responseEntity = userController.loginUser(userDTOShort);

        // Check if the response is successful
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode(), "Actual status code and expected code are not the same!");

        // Get the UserDTO from the response body
        UserDTO actualUserDTO = responseEntity.getBody();
        assertNotNull(actualUserDTO, "UserDTO should not be null");

        assertEquals(actualUserDTO.getEmail(), "test@example.com");
        assertEquals(actualUserDTO.getPassword(), "password123");
    }


}

