package com.example.ContentSubscription.service;


import com.example.ContentSubscription.Exceptions.NoPostFoundException;
import com.example.ContentSubscription.domain.Post;
import com.example.ContentSubscription.repository.PostRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class PostService {
    private final PostRepo postRepo;


    // Create a new post
    public Post createPost(Post post) {
        return postRepo.save(post);
    }

    // Get all posts
    public List<Post> getAllPosts() {
        return postRepo.findAll();
    }

    // Get a post by ID
    public Post readOne(Long postId) {
        return postRepo.findById(postId).orElseThrow(NoPostFoundException::new);
    }

    // Update a post
    /*public Post updatePost(Long postId, Post updatedPost) {
        Optional<Post> existingPost = postRepository.findById(postId);
        if (existingPost.isPresent()) {
            Post postToUpdate = existingPost.get();
            postToUpdate.setContent(updatedPost.getContent());
            postToUpdate.setCreator(updatedPost.getCreator());
            postToUpdate.setSubscriptionType(updatedPost.getSubscriptionType());
            return postRepository.save(postToUpdate);
        } else {
            // Handle the case where the post with the given ID is not found
            // You can throw an exception or handle it based on your requirements
            return null;
        }
    }*/

    // Delete a post by ID
    public void deletePost(Long postId) {
        if(!postRepo.existsById(postId))
            throw new NoPostFoundException();
        postRepo.deleteById(postId);
    }



}
