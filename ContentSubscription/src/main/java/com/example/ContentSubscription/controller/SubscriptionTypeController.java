package com.example.ContentSubscription.controller;


import com.example.ContentSubscription.converter.SubscriptionTypeConverter;
import com.example.ContentSubscription.domain.SubscriptionType;
import com.example.ContentSubscription.dtos.SubscriptionTypeDto;
import com.example.ContentSubscription.service.SubscriptionTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/subscriptionType")
public class SubscriptionTypeController {
    private final SubscriptionTypeService subscriptionTypeService;
    private final SubscriptionTypeConverter subscriptionTypeConverter;


    @PostMapping
    public ResponseEntity<SubscriptionTypeDto> addSubscriptionType(@RequestBody SubscriptionTypeDto subscriptionTypeDto)
    {
        SubscriptionType savedSubscriptionType = subscriptionTypeService.createSubscriptionType(subscriptionTypeConverter.convertDtoToEntity(subscriptionTypeDto));
        return new ResponseEntity<>(subscriptionTypeConverter.convertEntityToDto(savedSubscriptionType), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<SubscriptionTypeDto>> readAll()
    {
        List<SubscriptionType> allSubscriptionTypes = subscriptionTypeService.getAllSubscriptionTypes();
        return ResponseEntity.status(HttpStatus.OK).body(subscriptionTypeConverter.convertEntitiesToDtos(allSubscriptionTypes));
    }

    @GetMapping("/{subscriptionTypeId}")
    public ResponseEntity<SubscriptionTypeDto> readOne(@PathVariable Long subscriptionTypeId)
    {
        SubscriptionType foundSubscriptionType = subscriptionTypeService.getSubscriptionTypeById(subscriptionTypeId);
        return ResponseEntity.status(HttpStatus.OK).body(subscriptionTypeConverter.convertEntityToDto(foundSubscriptionType));
    }

    @DeleteMapping("/{subscriptionTypeId}")
    public ResponseEntity<String> deleteSubscriptionType(@PathVariable Long subscriptionTypeId)
    {
        subscriptionTypeService.deleteSubscriptionType(subscriptionTypeId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }




}
