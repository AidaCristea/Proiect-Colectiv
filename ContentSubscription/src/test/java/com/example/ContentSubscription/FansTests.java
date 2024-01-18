package com.example.ContentSubscription;

import com.example.ContentSubscription.controller.FanController;
import com.example.ContentSubscription.converter.FanConverter;
import com.example.ContentSubscription.domain.Creator;
import com.example.ContentSubscription.domain.Fan;
import com.example.ContentSubscription.dtos.FanDto;
import com.example.ContentSubscription.service.FanService;
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
public class FansTests {

    private List<Fan> fans = new ArrayList<>();

    @Mock
    private FanConverter fanConverter;

    @Mock
    private FanService fanService;

    @InjectMocks
    private FanController fanController;

    @Test
    public void testAddFan() {
        Fan fanToAdd = Fan.builder()
                .fanId(1L)
                .preferences("Sample Preferences")
                .subscriptionTypes(new ArrayList<>())
                .build();

        Fan savedFan = Fan.builder()
                .fanId(1L)
                .preferences("Sample Preferences")
                .subscriptionTypes(new ArrayList<>())
                .build();

        FanDto fanDtoToAdd = FanDto.builder()
                .preferences("Sample Preferences")
                .build();

        fans.add(savedFan);

        when(fanService.saveFan(fanToAdd)).thenReturn(savedFan);
        when(fanConverter.convertDtoToEntity(fanDtoToAdd)).thenReturn(fanToAdd);

        ResponseEntity<FanDto> response = fanController.addFan(fanDtoToAdd);
        verify(fanConverter, Mockito.times(1)).convertDtoToEntity(fanDtoToAdd);
        verify(fanService, Mockito.times(1)).saveFan(fanToAdd);

        assertAll(
                () -> assertEquals(HttpStatus.CREATED, response.getStatusCode(),
                        "Actual status code and expected code are not the same!")
        );
    }

    @Test
    public void testDeleteFan() {
        Long fanIdToDelete = 1L;
        fanController.deleteFan(fanIdToDelete);
        verify(fanService, Mockito.times(1)).deleteFan(fanIdToDelete);
    }

    @Test
    public void testReadOne() {
        Long fanIdToRead = 1L;
        Fan foundFan = Fan.builder()
                .fanId(fanIdToRead)
                .preferences("Sample Preferences")
                .subscriptionTypes(new ArrayList<>())
                .build();

        when(fanService.getFanById(fanIdToRead)).thenReturn(foundFan);

        ResponseEntity<FanDto> response = fanController.readOne(fanIdToRead);

        verify(fanService, Mockito.times(1)).getFanById(fanIdToRead);
        verify(fanConverter, Mockito.times(1)).convertEntityToDto(foundFan);

        assertAll(
                () -> assertEquals(HttpStatus.OK, response.getStatusCode(),
                        "Actual status code and expected code are not the same!")
        );
    }

    @Test
    public void testSeeCreators() {
        Long fanId = 1L;

        List<Creator> expectedCreators = new ArrayList<>();
        expectedCreators.add(Creator.builder().creatorId(1L).build());
        expectedCreators.add(Creator.builder().creatorId(2L).build());

        when(fanService.seeCreators(fanId)).thenReturn(expectedCreators);

        List<Creator> actualCreators = fanService.seeCreators(fanId);

        verify(fanService, Mockito.times(1)).seeCreators(fanId);

        assertEquals(expectedCreators, actualCreators,
                "Expected creators and actual creators do not match!");
    }
}
