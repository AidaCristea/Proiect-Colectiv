package com.example.ContentSubscription.controller;

import com.example.ContentSubscription.converter.CreatorConverter;
import com.example.ContentSubscription.converter.PostConverter;
import com.example.ContentSubscription.domain.Creator;
import com.example.ContentSubscription.domain.Post;
import com.example.ContentSubscription.dtos.CreatorDto;
import com.example.ContentSubscription.dtos.PostDto;
import com.example.ContentSubscription.service.CreatorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/creator")
public class CreatorController {

    private final CreatorService creatorService;
    private final CreatorConverter creatorConverter;
    private final PostConverter postConverter;



    @PostMapping
    public ResponseEntity<CreatorDto> addCreator(@RequestBody CreatorDto creatorDto) {
        Creator savedCreator = creatorService.createCreator(creatorConverter.convertDtoToEntity(creatorDto));
        return new ResponseEntity<>(creatorConverter.convertEntityToDto(savedCreator), HttpStatus.CREATED);
    }


    @GetMapping
    public ResponseEntity<List<CreatorDto>> readAll() {
        List<Creator> allCreators = creatorService.getAllCreators();
        return ResponseEntity.status(HttpStatus.OK).body(creatorConverter.convertEntitiesToDtos(allCreators));
    }


    @GetMapping("/{creatorId}")
    public ResponseEntity<CreatorDto> readOne(@PathVariable Long creatorId) {
        Creator foundCreator = creatorService.getCreatorById(creatorId);
        return ResponseEntity.status(HttpStatus.OK).body(creatorConverter.convertEntityToDto(foundCreator));
    }


    @DeleteMapping("/{creatorId}")
    public ResponseEntity<String> deleteCreator(@PathVariable Long creatorId) {
        creatorService.deleteCreator(creatorId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    @GetMapping("/seePosts/{creatorId}")
    public ResponseEntity<List<PostDto>> seePosts(@PathVariable Long creatorId)
    {
        // a list of posts of the creators the fan is subscribed to, based on subscription type
        List<Post> posts = creatorService.creatorSeesHisPosts(creatorId);
        System.out.println(posts);
        return ResponseEntity.status(HttpStatus.OK).body(postConverter.convertEntitiesToDtos(posts));
    }




}
