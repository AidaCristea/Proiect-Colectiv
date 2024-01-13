package com.example.ContentSubscription.controller;

import com.example.ContentSubscription.converter.UserConverter;
import com.example.ContentSubscription.domain.Fan;
import com.example.ContentSubscription.domain.User;
import com.example.ContentSubscription.dtos.FanDto;
import com.example.ContentSubscription.dtos.UserDTO;
import com.example.ContentSubscription.dtos.UserDTOShort;
import com.example.ContentSubscription.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
    private final UserService userService;
    private final UserConverter userConverter;

    @PostMapping("/signup")
    public ResponseEntity<UserDTO> addUser(@RequestBody UserDTO userDTO){
        User savedUser = userService.saveUser(userConverter.convertDtoToEntity(userDTO));
        return new ResponseEntity<>(userConverter.convertEntityToDto(savedUser), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<UserDTO>> readAll()
    {
        List<User> allUsers = userService.getAllUsers();
        return ResponseEntity.status(HttpStatus.OK).body(userConverter.convertEntitiesToDtos(allUsers));
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserDTO> readOne(@PathVariable Long userId)
    {
        User foundUser = userService.getUserById(userId);
        return ResponseEntity.status(HttpStatus.OK).body(userConverter.convertEntityToDto(foundUser));
    }
    @GetMapping("/login")
    public ResponseEntity<UserDTO> loginUser(@RequestBody UserDTOShort userDTOShort){

        User foundUser = userService.loginUser(userDTOShort.getEmail(), userDTOShort.getPassword());
        if(foundUser==null)
        {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
        }


        return ResponseEntity.status(HttpStatus.OK).body(userConverter.convertEntityToDto(foundUser));
    }




}
