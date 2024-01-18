package com.example.ContentSubscription;

import com.example.ContentSubscription.controller.PostController;
import com.example.ContentSubscription.converter.PostConverter;
import com.example.ContentSubscription.domain.Post;
import com.example.ContentSubscription.dtos.PostDto;
import com.example.ContentSubscription.service.PostService;
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
public class PostsTests {

    private List<Post> posts = new ArrayList<>();

    @Mock
    private PostConverter postConverter;

    @Mock
    private PostService postService;

    @InjectMocks
    private PostController postController;

    @Test
    public void testAddPost() {
        Post postToAdd = Post.builder()
                .postId(1L)
                .content("Sample Content")
                .build();

        Post savedPost = Post.builder()
                .postId(1L)
                .content("Sample Content")
                .build();

        PostDto postDtoToAdd = PostDto.builder()
                .content("Sample Content")
                .subscriptionTypeId(1L)
                .build();

        posts.add(savedPost);

        when(postService.createPost(postToAdd)).thenReturn(savedPost);
        when(postConverter.convertDtoToEntity(postDtoToAdd)).thenReturn(postToAdd);

        ResponseEntity<PostDto> response = postController.addPost(postDtoToAdd);
        verify(postConverter, Mockito.times(1)).convertDtoToEntity(postDtoToAdd);
        verify(postService, Mockito.times(1)).createPost(postToAdd);

        assertAll(
                () -> assertEquals(HttpStatus.CREATED, response.getStatusCode(),
                        "Actual status code and expected code are not the same!")
        );
    }

    @Test
    public void testDeletePost() {
        Long postIdToDelete = 1L;
        postController.deletePost(postIdToDelete);
        verify(postService, Mockito.times(1)).deletePost(postIdToDelete);
    }

    @Test
    public void testReadOne() {
        Long postIdToRead = 1L;
        Post foundPost = Post.builder()
                .postId(postIdToRead)
                .content("Sample Content")
                .subscriptionType(null)
                .build();

        when(postService.readOne(postIdToRead)).thenReturn(foundPost);

        ResponseEntity<PostDto> response = postController.readOne(postIdToRead);

        verify(postService, Mockito.times(1)).readOne(postIdToRead);
        verify(postConverter, Mockito.times(1)).convertEntityToDto(foundPost);

        assertAll(
                () -> assertEquals(HttpStatus.OK, response.getStatusCode(),
                        "Actual status code and expected code are not the same!")
        );
    }

    @Test
    public void testReadAllPosts() {
        List<Post> expectedPosts = new ArrayList<>();
        expectedPosts.add(Post.builder().postId(1L).build());
        expectedPosts.add(Post.builder().postId(2L).build());

        when(postService.getAllPosts()).thenReturn(expectedPosts);

        List<Post> actualPosts = postService.getAllPosts();

        verify(postService, Mockito.times(1)).getAllPosts();

        assertEquals(expectedPosts, actualPosts,
                "Expected posts and actual posts do not match!");
    }

}
