package com.example.ContentSubscription;

import com.example.ContentSubscription.controller.PostController;
import com.example.ContentSubscription.domain.Post;
import com.example.ContentSubscription.dtos.PostDto;
import com.example.ContentSubscription.service.PostService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureMockMvc
class PostTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PostService postService;

    @BeforeEach
    void setup() {
        // Mocking data for testing
        Post post = new Post();
        post.setPostId(1L);
        post.setContent("Test Content");
        PostDto postDto = new PostDto();
        postDto.setContent("Test Content");
        Long subscriptionTypeId = 1L;
        postDto.setContent("Test Content");
        postDto.setSubscriptionTypeId(subscriptionTypeId);

        List<Post> posts = new ArrayList<>();
        posts.add(post);

        // Mock the behavior of service methods
        when(postService.getAllPosts()).thenReturn(posts);
        when(postService.readOne(1L)).thenReturn(post);
        when(postService.createPost(any(Post.class))).thenReturn(post);
        // You can mock other methods for update and delete operations as needed.
    }

    @Test
    void testGetAllPosts() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/post"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].postId").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].content").value("Test Content"));
    }

    @Test
    void testGetPostById() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/post/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.postId").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.content").value("Test Content"));
    }

    @Test
    void testCreatePost() throws Exception {
        PostDto postDto = new PostDto();
        postDto.setContent("Test Content");
        Long subscriptionTypeId = 1L;
        postDto.setContent("Test Content");
        postDto.setSubscriptionTypeId(subscriptionTypeId);

        mockMvc.perform(MockMvcRequestBuilders.post("/post")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JsonUtil.toJson(postDto)))
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }

}

// You can create a utility class for JSON serialization/deserialization
class JsonUtil {
    public static String toJson(Object obj) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(obj);
    }
}
