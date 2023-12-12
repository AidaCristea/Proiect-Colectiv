package com.example.ContentSubscription.controller;


import com.example.ContentSubscription.converter.FanConverter;
import com.example.ContentSubscription.domain.Fan;
import com.example.ContentSubscription.dtos.FanDto;
import com.example.ContentSubscription.service.FanService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/fan")
public class FanController {

    private final FanService fanService;
    private final FanConverter fanConverter;

    @PostMapping
    public ResponseEntity<FanDto> addFan(@RequestBody FanDto fanDto)
    {
        Fan savedFan = fanService.saveFan(fanConverter.convertDtoToEntity(fanDto));
        return new ResponseEntity<>(fanConverter.convertEntityToDto(savedFan), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<FanDto>> readAll()
    {
        List<Fan> allFans = fanService.getAllFans();
        return ResponseEntity.status(HttpStatus.OK).body(fanConverter.convertEntitiesToDtos(allFans));
    }

    @GetMapping("/{fanId}")
    public ResponseEntity<FanDto> readOne(@PathVariable Long fanId)
    {
        Fan foundFan = fanService.getFanById(fanId);
        return ResponseEntity.status(HttpStatus.OK).body(fanConverter.convertEntityToDto(foundFan));
    }

    @DeleteMapping("/{fanId}")
    public ResponseEntity<String> deleteFan(@PathVariable Long fanId)
    {
        fanService.deleteFan(fanId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
