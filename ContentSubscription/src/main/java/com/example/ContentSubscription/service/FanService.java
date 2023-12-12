package com.example.ContentSubscription.service;


import com.example.ContentSubscription.Exceptions.NoFanFoundException;
import com.example.ContentSubscription.domain.Fan;
import com.example.ContentSubscription.repository.FanRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class FanService {
    private final FanRepo fanRepo;


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
        if(!fanRepo.existsById(fanId))
            throw new NoFanFoundException();
        fanRepo.deleteById(fanId);
    }


}
