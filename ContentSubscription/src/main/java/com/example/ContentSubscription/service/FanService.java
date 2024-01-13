package com.example.ContentSubscription.service;


import com.example.ContentSubscription.Exceptions.NoCreatorFoundException;
import com.example.ContentSubscription.Exceptions.NoFanFoundException;
import com.example.ContentSubscription.Exceptions.NoPostFoundException;
import com.example.ContentSubscription.domain.Creator;
import com.example.ContentSubscription.domain.Fan;
import com.example.ContentSubscription.domain.Post;
import com.example.ContentSubscription.repository.CreatorRepo;
import com.example.ContentSubscription.repository.FanRepo;
import com.example.ContentSubscription.repository.PostRepo;
import com.example.ContentSubscription.repository.SubscriptionTypeRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class FanService {
    private final FanRepo fanRepo;
    private final PostRepo postRepo;
    private final SubscriptionTypeRepo subscriptionTypeRepo;
    private final CreatorRepo creatorRepo;


    public List<Fan> getAllFans() {
        return fanRepo.findAll();
    }

    public Fan getFanById(Long fanId) {
        return fanRepo.findById(fanId).orElseThrow(NoFanFoundException::new);
    }

    public Fan saveFan(Fan fan) {
        return fanRepo.save(fan);
    }

    public void deleteFan(Long fanId) {
        if (!fanRepo.existsById(fanId))
            throw new NoFanFoundException();
        fanRepo.deleteById(fanId);
    }

    public List<Creator> seeCreators(Long fanId) {
        List<Creator> allCreatorsThatTheFanIsSubscribedTo = new ArrayList<>();

        List<Long> subscriptionTypeIdsForTheFan = subscriptionTypeRepo.findByFanId(fanId);

        for (Long stId : subscriptionTypeIdsForTheFan) {
            List<Long> creatorsIds = subscriptionTypeRepo.findCreatorBySubscriptionId(stId);

            for (Long creatorId : creatorsIds) {
                allCreatorsThatTheFanIsSubscribedTo.add(creatorRepo.findById(creatorId).orElseThrow(NoCreatorFoundException::new));
            }
        }


        return allCreatorsThatTheFanIsSubscribedTo.stream()
                .distinct()
                .collect(Collectors.toList());

    }


    public List<Creator> seeCreatorsNotSubscribedTo(Long fanId)
    {
        //fans not subscribed to
        List<Creator> allCreatorsThatTheFanIsNotSubscribedTo = new ArrayList<>();
        List<Creator> allCreators = creatorRepo.findAll();

        //fans subscribed to
        List<Creator> subscribedTo = seeCreators(fanId);

        for(Creator cr: allCreators)
        {
            if(!subscribedTo.contains(cr))
            {
                allCreatorsThatTheFanIsNotSubscribedTo.add(cr);
            }
        }

        return allCreatorsThatTheFanIsNotSubscribedTo;

    }

    public List<Post> seePosts(Long fanId) {
        //select post where subscriptionType in (select subscription type where idFan = fanId)

        List<Post> allPostsThatTheFanCanSee = new ArrayList<>();

        System.out.println(subscriptionTypeRepo.findByFanId(fanId));

        List<Long> subscriptionTypeIdsForTheFan = subscriptionTypeRepo.findByFanId(fanId);


        for (Long stId : subscriptionTypeIdsForTheFan) {
            List<Long> postsIds = postRepo.findBySubscriptionTypeId(stId);
            for (Long postId : postsIds) {
                allPostsThatTheFanCanSee.add(postRepo.findById(postId).orElseThrow(NoPostFoundException::new));
            }

            System.out.println(postRepo.findBySubscriptionTypeId(stId));
        }
        return allPostsThatTheFanCanSee;

    }

}
