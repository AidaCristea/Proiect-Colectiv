package com.example.ContentSubscription.converter;


import com.example.ContentSubscription.domain.Fan;
import com.example.ContentSubscription.dtos.FanDto;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;

@Component
public class FanConverter {

    public FanDto convertEntityToDto(Fan entity)
    {
        return FanDto.builder()
                .fanId(entity.getFanId())
                .preferences(entity.getPreferences())
                .build();
    }

    public Fan convertDtoToEntity(FanDto entity)
    {
        return Fan.builder()
                .fanId(entity.getFanId())
                .preferences(entity.getPreferences())
                .build();
    }


    public List<Fan> convertDtosToEntities(Collection<FanDto> modelDtos)
    {
        return modelDtos.stream()
                .map(this::convertDtoToEntity)
                .toList();
    }

    public List<FanDto> convertEntitiesToDtos(Collection<Fan> model)
    {
        return model.stream()
                .map(this::convertEntityToDto)
                .toList();
    }

}
