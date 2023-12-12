package com.example.ContentSubscription.service;


import com.example.ContentSubscription.Exceptions.NoCreatorFoundException;
import com.example.ContentSubscription.domain.Creator;
import com.example.ContentSubscription.repository.CreatorRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CreatorService {

    private final CreatorRepo creatorRepo;

    // Create a new creator
    public Creator createCreator(Creator creator) {
        return creatorRepo.save(creator);
    }

    // Get all creators
    public List<Creator> getAllCreators() {
        return creatorRepo.findAll();
    }

    // Get a creator by ID
    public Creator getCreatorById(Long creatorId) {
        return creatorRepo.findById(creatorId).orElseThrow(NoCreatorFoundException::new);
    }

    // Update a creator
   /* public Creator updateCreator(Long creatorId, Creator updatedCreator) {
        Optional<Creator> existingCreator = creatorRepo.findById(creatorId);
        if (existingCreator.isPresent()) {
            Creator creatorToUpdate = existingCreator.get();
            creatorToUpdate.setBio(updatedCreator.getBio());
            // You can add more fields to update based on your requirements
            return creatorRepo.save(creatorToUpdate);
        } else {
            // Handle the case where the creator with the given ID is not found
            // You can throw an exception or handle it based on your requirements
            return null;
        }
    }*/

    // Delete a creator by ID
    public void deleteCreator(Long creatorId) {
        if(!creatorRepo.existsById(creatorId))
            throw new NoCreatorFoundException();
        creatorRepo.deleteById(creatorId);
    }



}
