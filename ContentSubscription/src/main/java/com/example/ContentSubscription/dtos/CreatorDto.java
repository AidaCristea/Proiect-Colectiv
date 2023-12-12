package com.example.ContentSubscription.dtos;


import com.example.ContentSubscription.domain.Fan;
import com.example.ContentSubscription.domain.Post;
import com.example.ContentSubscription.domain.SubscriptionType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreatorDto {
    private Long creatorId;
    private String bio;
    //private List<SubscriptionType> subscriptionTypes;
}
