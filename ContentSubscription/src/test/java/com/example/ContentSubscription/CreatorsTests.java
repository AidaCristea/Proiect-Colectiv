package com.example.ContentSubscription;

import com.example.ContentSubscription.controller.CreatorController;
import com.example.ContentSubscription.converter.CreatorConverter;
import com.example.ContentSubscription.domain.Creator;
import com.example.ContentSubscription.dtos.CreatorDto;
import com.example.ContentSubscription.service.CreatorService;
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
public class CreatorsTests {

    private List<Creator> creators = new ArrayList<>();

    @Mock
    private CreatorConverter creatorConverter;

    @Mock
    private CreatorService creatorService;

    @InjectMocks
    private CreatorController creatorController;

    @Test
    public void testCreateCreator() {

        Creator creatorToAdd = Creator.builder()
                .creatorId(1L)
                .bio("Sample Bio")
                .priceUltimate(100L)
                .priceLite(50L)
                .pricePro(75L)
                .photoURL("url")
                .subscriptionTypes(new ArrayList<>())
                .build();

        Creator addedCreator = Creator.builder()
                .creatorId(1L)
                .bio("Sample Bio")
                .priceUltimate(100L)
                .priceLite(50L)
                .pricePro(75L)
                .photoURL("url")
                .subscriptionTypes(new ArrayList<>())
                .build();

        CreatorDto creatorDtoToAdd = CreatorDto.builder()
                .bio("Sample Bio")
                .priceUltimate(100L)
                .priceLite(50L)
                .pricePro(75L)
                .photoURL("url")
                .build();

        creators.add(addedCreator);

        when(creatorService.createCreator(creatorToAdd)).thenReturn(addedCreator);
        when(creatorConverter.convertDtoToEntity(creatorDtoToAdd)).thenReturn(creatorToAdd);

        ResponseEntity<CreatorDto> response = creatorController.addCreator(creatorDtoToAdd);
        verify(creatorConverter, Mockito.times(1)).convertDtoToEntity(creatorDtoToAdd);
        verify(creatorService, Mockito.times(1)).createCreator(creatorToAdd);

        assertAll(
                () -> assertEquals(HttpStatus.CREATED, response.getStatusCode(),
                        "Actual status code and expected code are not the same!")
        );
    }

    @Test
    public void testDeleteCreator() {
        Long creatorIdToDelete = 1L;
        creatorController.deleteCreator(creatorIdToDelete);
        verify(creatorService, Mockito.times(1)).deleteCreator(creatorIdToDelete);
    }

    @Test
    public void testReadOne() {
        Long creatorIdToRead = 1L;
        Creator foundCreator = Creator.builder()
                .creatorId(creatorIdToRead)
                .bio("Sample Bio")
                .priceUltimate(100L)
                .priceLite(50L)
                .pricePro(75L)
                .photoURL("url")
                .subscriptionTypes(new ArrayList<>())
                .build();

        when(creatorService.getCreatorById(creatorIdToRead)).thenReturn(foundCreator);

        ResponseEntity<CreatorDto> response = creatorController.readOne(creatorIdToRead);

        verify(creatorService, Mockito.times(1)).getCreatorById(creatorIdToRead);
        verify(creatorConverter, Mockito.times(1)).convertEntityToDto(foundCreator);

        assertAll(
                () -> assertEquals(HttpStatus.OK, response.getStatusCode(),
                        "Actual status code and expected code are not the same!")
        );
    }
}
