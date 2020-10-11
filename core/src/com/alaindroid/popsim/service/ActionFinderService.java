package com.alaindroid.popsim.service;

import com.alaindroid.popsim.model.action.ActionType;
import com.alaindroid.popsim.service.seeker.*;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ActionFinderService {
    private final FoodSeekerService foodSeekerService;
    private final WanderService wanderService;
    private final EatService eatService;
    private final DeadActionService deadActionService;
    public ActionService finderService(ActionType actionType) {
        switch (actionType) {
            case WANDER:
                return wanderService;
            case FIND_FOOD:
                return foodSeekerService;
            case EAT:
                return eatService;
            case DEAD:
                return deadActionService;
        }
        return null;
    }

}
