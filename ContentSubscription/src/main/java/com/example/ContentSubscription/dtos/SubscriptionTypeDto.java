package com.example.ContentSubscription.dtos;

import com.example.ContentSubscription.domain.Post;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SubscriptionTypeDto {
    private Long subscriptionTypeId;
    private String type;
    private double price;
    private String description;
    private Long creatorId;
    private Long fanId;
}
