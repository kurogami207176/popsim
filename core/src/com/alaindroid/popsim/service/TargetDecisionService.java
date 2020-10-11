package com.alaindroid.popsim.service;

import com.alaindroid.popsim.model.Creature;
import com.alaindroid.popsim.service.seeker.FoodSeekerService;
import com.alaindroid.popsim.service.seeker.TargetFinderService;
import com.alaindroid.popsim.service.seeker.WanderService;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class TargetDecisionService {

    private final FoodSeekerService foodSeekerService;
    private final WanderService wanderService;

    public TargetFinderService findTarget(Creature creature, List<Creature> otherLives) {
        return wanderService;
    }
}
