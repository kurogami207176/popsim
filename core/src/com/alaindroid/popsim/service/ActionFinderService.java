package com.alaindroid.popsim.service;

import com.alaindroid.popsim.model.action.ActionType;
import com.alaindroid.popsim.service.seeker.EatService;
import com.alaindroid.popsim.service.seeker.FoodSeekerService;
import com.alaindroid.popsim.service.seeker.ActionService;
import com.alaindroid.popsim.service.seeker.WanderService;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ActionFinderService {
    private final FoodSeekerService foodSeekerService;
    private final WanderService wanderService;
    private final EatService eatService;
    public ActionService finderService(ActionType actionType) {
        switch (actionType) {
            case WANDER:
                return wanderService;
            case FIND_FOOD:
                return foodSeekerService;
            case EAT:
                return eatService;
        }
        return null;
    }

}
