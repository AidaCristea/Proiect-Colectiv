package com.example.ContentSubscription;

import com.example.ContentSubscription.controller.SubscriptionTypeController;
import com.example.ContentSubscription.converter.SubscriptionTypeConverter;
import com.example.ContentSubscription.domain.SubscriptionType;
import com.example.ContentSubscription.dtos.SubscriptionTypeDto;
import com.example.ContentSubscription.service.SubscriptionTypeService;
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
public class SubscriptionTypesTests {

    private List<SubscriptionType> subscriptionTypes = new ArrayList<>();

    @Mock
    private SubscriptionTypeConverter subscriptionTypeConverter;

    @Mock
    private SubscriptionTypeService subscriptionTypeService;

    @InjectMocks
    private SubscriptionTypeController subscriptionTypeController;

    @Test
    public void testAddSubscriptionType() {
        SubscriptionType subscriptionTypeToAdd = SubscriptionType.builder()
                .subscriptionTypeId(1L)
                .type(SubscriptionType.Type.ULTIMATE)
                .price(100.0)
                .description("Sample Description")
                .build();

        SubscriptionType savedSubscriptionType = SubscriptionType.builder()
                .subscriptionTypeId(1L)
                .type(SubscriptionType.Type.ULTIMATE)
                .price(100.0)
                .description("Sample Description")
                .build();

        SubscriptionTypeDto subscriptionTypeDtoToAdd = SubscriptionTypeDto.builder()
                .type(SubscriptionType.Type.ULTIMATE)
                .price(100.0)
                .description("Sample Description")
                .creatorId(1L)
                .fanId(2L)
                .build();

        subscriptionTypes.add(savedSubscriptionType);

        when(subscriptionTypeService.createSubscriptionType(subscriptionTypeToAdd)).thenReturn(savedSubscriptionType);
        when(subscriptionTypeConverter.convertDtoToEntity(subscriptionTypeDtoToAdd)).thenReturn(subscriptionTypeToAdd);

        ResponseEntity<SubscriptionTypeDto> response = subscriptionTypeController.addSubscriptionType(subscriptionTypeDtoToAdd);
        verify(subscriptionTypeConverter, Mockito.times(1)).convertDtoToEntity(subscriptionTypeDtoToAdd);
        verify(subscriptionTypeService, Mockito.times(1)).createSubscriptionType(subscriptionTypeToAdd);

        assertAll(
                () -> assertEquals(HttpStatus.CREATED, response.getStatusCode(),
                        "Actual status code and expected code are not the same!")
        );
    }

    @Test
    public void testDeleteSubscriptionType() {
        Long subscriptionTypeIdToDelete = 1L;

        subscriptionTypeService.deleteSubscriptionType(subscriptionTypeIdToDelete);

        verify(subscriptionTypeService, Mockito.times(1)).deleteSubscriptionType(subscriptionTypeIdToDelete);
    }

    @Test
    public void testDeleteSubscriptionTypeInController() {
        Long subscriptionTypeIdToDelete = 1L;

        ResponseEntity<String> response = subscriptionTypeController.deleteSubscriptionType(subscriptionTypeIdToDelete);

        verify(subscriptionTypeService, Mockito.times(1)).deleteSubscriptionType(subscriptionTypeIdToDelete);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode(), "Actual status code and expected code are not the same!");
    }


    @Test
    public void testReadAllSubscriptionTypes() {
        List<SubscriptionType> expectedSubscriptionTypes = new ArrayList<>();
        expectedSubscriptionTypes.add(SubscriptionType.builder().subscriptionTypeId(1L).build());
        expectedSubscriptionTypes.add(SubscriptionType.builder().subscriptionTypeId(2L).build());

        when(subscriptionTypeService.getAllSubscriptionTypes()).thenReturn(expectedSubscriptionTypes);

        ResponseEntity<List<SubscriptionTypeDto>> response = subscriptionTypeController.readAll();

        verify(subscriptionTypeService, Mockito.times(1)).getAllSubscriptionTypes();

        assertEquals(HttpStatus.OK, response.getStatusCode(), "Actual status code and expected code are not the same!");
    }


    @Test
    public void testReadAllSubscriptionTypesInController() {
        List<SubscriptionType> expectedSubscriptionTypes = new ArrayList<>();
        expectedSubscriptionTypes.add(SubscriptionType.builder().subscriptionTypeId(1L).build());
        expectedSubscriptionTypes.add(SubscriptionType.builder().subscriptionTypeId(2L).build());

        when(subscriptionTypeService.getAllSubscriptionTypes()).thenReturn(expectedSubscriptionTypes);

        ResponseEntity<List<SubscriptionTypeDto>> response = subscriptionTypeController.readAll();

        verify(subscriptionTypeService, Mockito.times(1)).getAllSubscriptionTypes();
        verify(subscriptionTypeConverter, Mockito.times(1)).convertEntitiesToDtos(expectedSubscriptionTypes);

        assertEquals(HttpStatus.OK, response.getStatusCode(), "Actual status code and expected code are not the same!");
    }

}

