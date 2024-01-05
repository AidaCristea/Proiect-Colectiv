package com.example.ContentSubscription.converter;

import com.example.ContentSubscription.Exceptions.NoSubscriptionTypeFound;
import com.example.ContentSubscription.domain.Creator;
import com.example.ContentSubscription.domain.Post;
import com.example.ContentSubscription.domain.SubscriptionType;
import com.example.ContentSubscription.dtos.PostDto;
import com.example.ContentSubscription.repository.SubscriptionTypeRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;

@Component
@AllArgsConstructor
public class PostConverter {

    private final SubscriptionTypeRepo subscriptionTypeRepo;

    public PostDto convertEntityToDto(Post entity)
    {
        return PostDto.builder()
                .postId(entity.getPostId())
                .content(entity.getContent())
                .subscriptionTypeId(entity.getSubscriptionType().getSubscriptionTypeId())
                .build();
    }

    public Post convertDtoToEntity(PostDto entity)
    {
        SubscriptionType foundSubscriptionType = subscriptionTypeRepo.findById(entity.getSubscriptionTypeId()).orElseThrow(NoSubscriptionTypeFound::new);

        return Post.builder()
                .postId(entity.getPostId())
                .content(entity.getContent())
                .subscriptionType(foundSubscriptionType)
                .build();
    }

    public List<Post> convertDtosToEntities(Collection<PostDto> modelDtos)
    {
        return modelDtos.stream()
                .map(this::convertDtoToEntity)
                .toList();
    }

    public List<PostDto> convertEntitiesToDtos(Collection<Post> model)
    {
        return model.stream()
                .map(this::convertEntityToDto)
                .toList();
    }

}
