package com.example.ContentSubscription.converter;

import com.example.ContentSubscription.Exceptions.NoCreatorFoundException;
import com.example.ContentSubscription.Exceptions.NoFanFoundException;
import com.example.ContentSubscription.domain.Creator;
import com.example.ContentSubscription.domain.Fan;
import com.example.ContentSubscription.domain.SubscriptionType;
import com.example.ContentSubscription.dtos.SubscriptionTypeDto;
import com.example.ContentSubscription.repository.CreatorRepo;
import com.example.ContentSubscription.repository.FanRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;

@Component
@AllArgsConstructor
public class SubscriptionTypeConverter {
    private final CreatorRepo creatorRepo;
    private final FanRepo fanRepo;

    public SubscriptionTypeDto convertEntityToDto(SubscriptionType entity)
    {
        return SubscriptionTypeDto.builder()
                .subscriptionTypeId(entity.getSubscriptionTypeId())
                .type(entity.getType())
                .price(entity.getPrice())
                .description(entity.getDescription())
                .creatorId(entity.getCreator().getCreatorId())
                .fanId(entity.getFan().getFanId())
                .build();
    }

    public SubscriptionType convertDtoToEntity(SubscriptionTypeDto entity)
    {
        Creator foundCreator = creatorRepo.findById(entity.getCreatorId()).orElseThrow(NoCreatorFoundException::new);
        Fan foundFan = fanRepo.findById(entity.getFanId()).orElseThrow(NoFanFoundException::new);

        return SubscriptionType.builder()
                .subscriptionTypeId(entity.getSubscriptionTypeId())
                .type(entity.getType())
                .price(entity.getPrice())
                .description(entity.getDescription())
                .creator(foundCreator)
                .fan(foundFan)
                .build();
    }

    public List<SubscriptionType> convertDtosToEntities(Collection<SubscriptionTypeDto> modelDtos)
    {
        return modelDtos.stream()
                .map(this::convertDtoToEntity)
                .toList();
    }

    public List<SubscriptionTypeDto> convertEntitiesToDtos(Collection<SubscriptionType> model)
    {
        return model.stream()
                .map(this::convertEntityToDto)
                .toList();
    }
}
