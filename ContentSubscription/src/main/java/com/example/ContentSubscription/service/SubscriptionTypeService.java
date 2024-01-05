package com.example.ContentSubscription.service;

import com.example.ContentSubscription.Exceptions.NoSubscriptionTypeFound;
import com.example.ContentSubscription.domain.SubscriptionType;
import com.example.ContentSubscription.repository.SubscriptionTypeRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@RequiredArgsConstructor
@Service
public class SubscriptionTypeService {

    private final SubscriptionTypeRepo subscriptionTypeRepo;


    // Create a new subscription type
    public SubscriptionType createSubscriptionType(SubscriptionType subscriptionType) {
        //System.out.println(subscriptionType);

        //complete the price field with the price given by the type and the creatorId
        Long price = subscriptionTypeRepo.price(subscriptionType.getCreator().getCreatorId(), subscriptionType.getType().toString());
        subscriptionType.setPrice(price);

        System.out.println("afisare "+price);


        return subscriptionTypeRepo.save(subscriptionType);
        //return null;
    }

    // Get all subscription types
    public List<SubscriptionType> getAllSubscriptionTypes() {
        return subscriptionTypeRepo.findAll();
    }

    // Get a subscription type by ID
    public SubscriptionType getSubscriptionTypeById(Long subscriptionTypeId) {
        return subscriptionTypeRepo.findById(subscriptionTypeId).orElseThrow(NoSubscriptionTypeFound::new);
    }

    // Update a subscription type
    /*public SubscriptionType updateSubscriptionType(Long subscriptionTypeId, SubscriptionType updatedSubscriptionType) {
        Optional<SubscriptionType> existingSubscriptionType = subscriptionTypeRepository.findById(subscriptionTypeId);
        if (existingSubscriptionType.isPresent()) {
            SubscriptionType subscriptionTypeToUpdate = existingSubscriptionType.get();
            subscriptionTypeToUpdate.setType(updatedSubscriptionType.getType());
            subscriptionTypeToUpdate.setPrice(updatedSubscriptionType.getPrice());
            subscriptionTypeToUpdate.setDescription(updatedSubscriptionType.getDescription());
            // You can add more fields to update based on your requirements
            return subscriptionTypeRepository.save(subscriptionTypeToUpdate);
        } else {
            // Handle the case where the subscription type with the given ID is not found
            // You can throw an exception or handle it based on your requirements
            return null;
        }
    }*/

    // Delete a subscription type by ID
    public void deleteSubscriptionType(Long subscriptionTypeId) {
        subscriptionTypeRepo.deleteById(subscriptionTypeId);
    }




}
