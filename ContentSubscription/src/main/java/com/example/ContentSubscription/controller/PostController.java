package com.example.ContentSubscription.controller;

import com.example.ContentSubscription.converter.PostConverter;
import com.example.ContentSubscription.domain.Post;
import com.example.ContentSubscription.dtos.PostDto;
import com.example.ContentSubscription.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/post")
public class PostController {

    private final PostService postService;
    private final PostConverter postConverter;


    @PostMapping
    public ResponseEntity<PostDto> addPost(@RequestBody PostDto postDto) {
        Post savedPost = postService.createPost(postConverter.convertDtoToEntity(postDto));
        return new ResponseEntity<>(postConverter.convertEntityToDto(savedPost), HttpStatus.CREATED);
    }


    @GetMapping
    public ResponseEntity<List<PostDto>> readAll() {
        List<Post> allPosts = postService.getAllPosts();
        return ResponseEntity.status(HttpStatus.OK).body(postConverter.convertEntitiesToDtos(allPosts));
    }


    @GetMapping("/{postId}")
    public ResponseEntity<PostDto> readOne(@PathVariable Long postId) {
        Post foundPost = postService.readOne(postId);
        return ResponseEntity.status(HttpStatus.OK).body(postConverter.convertEntityToDto(foundPost));
    }


    @DeleteMapping("/{postId}")
    public ResponseEntity<String> deletePost(@PathVariable Long postId) {
        postService.deletePost(postId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
