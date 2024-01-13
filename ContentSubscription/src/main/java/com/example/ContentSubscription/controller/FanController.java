package com.example.ContentSubscription.controller;


import com.example.ContentSubscription.converter.CreatorConverter;
import com.example.ContentSubscription.converter.FanConverter;
import com.example.ContentSubscription.converter.PostConverter;
import com.example.ContentSubscription.domain.Creator;
import com.example.ContentSubscription.domain.Fan;
import com.example.ContentSubscription.domain.Post;
import com.example.ContentSubscription.dtos.CreatorDto;
import com.example.ContentSubscription.dtos.FanDto;
import com.example.ContentSubscription.dtos.PostDto;
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
    private final PostConverter postConverter;
    private final CreatorConverter creatorConverter;

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

    @GetMapping("/seePosts/{fanId}")
    public ResponseEntity<List<PostDto>> seePosts(@PathVariable Long fanId)
    {
        // a list of posts of the creators the fan is subscribed to, based on subscription type
        List<Post> posts = fanService.seePosts(fanId);
        System.out.println(posts);
        return ResponseEntity.status(HttpStatus.OK).body(postConverter.convertEntitiesToDtos(posts));
    }

    @GetMapping("/seeCreators/{fanId}")
    public ResponseEntity<List<CreatorDto>> seeCreators(@PathVariable Long fanId)
    {
        List<Creator> creators = fanService.seeCreators(fanId);
        return ResponseEntity.status(HttpStatus.OK).body(creatorConverter.convertEntitiesToDtos(creators));

    }


    @GetMapping("/creatorsNotSubscribedTo/{fanId}")
    public ResponseEntity<List<CreatorDto>> seeNotSubscribedTo(@PathVariable Long fanId)
    {
        List<Creator> creators = fanService.seeCreatorsNotSubscribedTo(fanId);
        return ResponseEntity.status(HttpStatus.OK).body(creatorConverter.convertEntitiesToDtos(creators));

    }



}
