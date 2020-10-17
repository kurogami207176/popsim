package com.alaindroid.popsim.service;

import com.alaindroid.popsim.model.Creature;
import com.alaindroid.popsim.model.action.ActionType;
import com.alaindroid.popsim.model.stats.CreatureSnapshot;
import com.alaindroid.popsim.util.CreatureDistanceUtil;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import lombok.experimental.Accessors;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class ActionDecisionService {

    public ActionDecision actionType(Creature creature, List<CreatureSnapshot> otherLives) {
        boolean canMove = creature.movementRange().canMove();
        ActionType actionType;
        Optional<CreatureSnapshot> closest = CreatureDistanceUtil.findClosestEdibleLivingCreature(creature, otherLives);
        if (!creature.body().alive()) {
            actionType = ActionType.DEAD;
        }
        else if (canMove) {
            boolean isHungry = creature.hunger().isHungry();
            boolean isReach = CreatureDistanceUtil.canReach(creature, closest);
            actionType = isHungry
                    ? isReach
                        ? ActionType.EAT
                        : ActionType.FIND_FOOD
                    : ActionType.WANDER;
            System.out.println(date() + " " + creature.name() + " decided to " + actionType + ". "
                    + (closest.isPresent()? "Target: " + closest.get().type() : "NO Target found."));
        }
        else {
            actionType = ActionType.EAT;
        }
        return new ActionDecision(actionType, closest);
    }

    private String date() {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S").format(new Date());
    }

    @Value
    @Accessors(fluent = true)
    @AllArgsConstructor
    public static class ActionDecision {
        private ActionType actionType;
        private Optional<CreatureSnapshot> closest;
    }
}
