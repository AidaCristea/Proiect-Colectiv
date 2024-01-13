package com.example.ContentSubscription.converter;


import com.example.ContentSubscription.domain.Creator;
import com.example.ContentSubscription.dtos.CreatorDto;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;

@Component
public class CreatorConverter {

    public CreatorDto convertEntityToDto(Creator entity)
    {
        return CreatorDto.builder()
                .creatorId(entity.getCreatorId())
                .bio(entity.getBio())
                .priceUltimate(entity.getPriceUltimate())
                .priceLite(entity.getPriceLite())
                .pricePro(entity.getPricePro())
                .photoURL(entity.getPhotoURL())
                .build();
    }

    public Creator convertDtoToEntity(CreatorDto entity)
    {
        return Creator.builder()
                .creatorId(entity.getCreatorId())
                .bio(entity.getBio())
                .priceUltimate(entity.getPriceUltimate())
                .priceLite(entity.getPriceLite())
                .pricePro(entity.getPricePro())
                .photoURL(entity.getPhotoURL())
                .build();
    }

    public List<Creator> convertDtosToEntities(Collection<CreatorDto> modelDtos)
    {
        return modelDtos.stream()
                .map(this::convertDtoToEntity)
                .toList();
    }

    public List<CreatorDto> convertEntitiesToDtos(Collection<Creator> model)
    {
        return model.stream()
                .map(this::convertEntityToDto)
                .toList();
    }


}
