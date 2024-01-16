package com.example.ContentSubscription;

import com.example.ContentSubscription.controller.SubscriptionTypeController;
import com.example.ContentSubscription.domain.SubscriptionType;
import com.example.ContentSubscription.dtos.SubscriptionTypeDto;
import com.example.ContentSubscription.service.SubscriptionTypeService;
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
public class SubscriptionTypeTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private SubscriptionTypeService subscriptionTypeService;

    private List<SubscriptionType> subscriptionTypeList;

    @BeforeEach
    public void setup() {
        // Initialize a list of subscription types for testing
        subscriptionTypeList = new ArrayList<>();
        SubscriptionType subscriptionType1 = SubscriptionType.builder()
                .subscriptionTypeId(1L)
                .type(SubscriptionType.Type.ULTIMATE)
                .price(10.0)
                .description("Ultimate Subscription")
                .build();
        SubscriptionType subscriptionType2 = SubscriptionType.builder()
                .subscriptionTypeId(2L)
                .type(SubscriptionType.Type.LITE)
                .price(5.0)
                .description("Lite Subscription")
                .build();
        subscriptionTypeList.add(subscriptionType1);
        subscriptionTypeList.add(subscriptionType2);
    }

    @Test
    public void testAddSubscriptionType() throws Exception {
        // Prepare a SubscriptionTypeDto for adding a subscription type
        SubscriptionTypeDto subscriptionTypeDto = SubscriptionTypeDto.builder()
                .subscriptionTypeId(3L)
                .type(SubscriptionType.Type.PRO)
                .price(8.0)
                .description("Pro Subscription")
                .creatorId(1L)
                .fanId(1L)
                .build();

        // Mock the service to return the saved subscription type
        Mockito.when(subscriptionTypeService.createSubscriptionType(Mockito.any(SubscriptionType.class)))
                .thenReturn(subscriptionTypeList.get(0));

        // Perform the POST request to add a subscription type
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/subscriptionType")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(subscriptionTypeDto)))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.subscriptionTypeId").value(1L))
                .andExpect(MockMvcResultMatchers.jsonPath("$.type").value("ULTIMATE"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.price").value(10.0))
                .andExpect(MockMvcResultMatchers.jsonPath("$.description").value("Ultimate Subscription"));
    }

    @Test
    public void testReadAllSubscriptionTypes() throws Exception {
        // Mock the service to return the list of subscription types
        Mockito.when(subscriptionTypeService.getAllSubscriptionTypes()).thenReturn(subscriptionTypeList);

        // Perform the GET request to retrieve all subscription types
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/subscriptionType")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").isArray())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].subscriptionTypeId").value(1L))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].type").value("ULTIMATE"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].price").value(10.0))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].description").value("Ultimate Subscription"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].subscriptionTypeId").value(2L))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].type").value("LITE"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].price").value(5.0))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].description").value("Lite Subscription"));
    }

}
