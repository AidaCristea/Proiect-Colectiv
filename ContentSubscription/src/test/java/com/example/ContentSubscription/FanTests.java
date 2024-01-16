package com.example.ContentSubscription;

import com.example.ContentSubscription.controller.FanController;
import com.example.ContentSubscription.domain.Fan;
import com.example.ContentSubscription.dtos.FanDto;
import com.example.ContentSubscription.service.FanService;
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

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@AutoConfigureMockMvc
public class FanTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private FanService fanService;

    private List<Fan> fanList;

    @BeforeEach
    public void setup() {
        // Initialize a list of fans for testing
        fanList = new ArrayList<>();
        fanList.add(Fan.builder().fanId(1L).preferences("Preference 1").build());
        fanList.add(Fan.builder().fanId(2L).preferences("Preference 2").build());
    }

    @Test
    public void testAddFan() throws Exception {
        // Prepare a FanDto for adding a fan
        FanDto fanDto = FanDto.builder().fanId(3L).preferences("Preference 3").build();

        // Mock the service to return the saved fan
        Mockito.when(fanService.saveFan(Mockito.any(Fan.class))).thenReturn(fanList.get(0));

        // Perform the POST request to add a fan
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/fan")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(fanDto)))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.fanId").value(1L))
                .andExpect(MockMvcResultMatchers.jsonPath("$.preferences").value("Preference 1"));
    }

    @Test
    public void testReadAllFans() throws Exception {
        // Mock the service to return the list of fans
        Mockito.when(fanService.getAllFans()).thenReturn(fanList);

        // Perform the GET request to retrieve all fans
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/fan")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").isArray())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].fanId").value(1L))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].preferences").value("Preference 1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].fanId").value(2L))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].preferences").value("Preference 2"));
    }

}
