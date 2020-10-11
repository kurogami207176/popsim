package com.alaindroid.popsim.service;

import com.alaindroid.popsim.draw.DrawBox;
import com.alaindroid.popsim.model.Creature;
import com.alaindroid.popsim.model.Terrain;
import com.alaindroid.popsim.model.action.Action;
import com.alaindroid.popsim.model.action.ActionType;
import com.alaindroid.popsim.model.features.Location;
import com.alaindroid.popsim.model.features.Mobility;
import com.alaindroid.popsim.service.seeker.ActionService;
import com.alaindroid.popsim.util.DistanceUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class MobilityService {

    private final ActionDecisionService actionDecisionService;
    private final ActionFinderService actionFinderService;
    private final DrawBox drawBox;

    public void moveCreatures(float deltaTime, List<Creature> allLives, Terrain terrain) {
        for (int i = 0; i <allLives.size(); i++) {
            Creature thisCreature = allLives.get(i);
            List<Creature> otherCreatures = new ArrayList<>(allLives);
            otherCreatures.remove(i);

            action(deltaTime, thisCreature, otherCreatures, terrain);
            move(deltaTime, thisCreature);
        }
    }

    private void move(float deltaTime, Creature creature) {
        if(creature.reach().canReach(creature.location(), creature.action().target())) {
            return;
        }

        Movement movement = calculateMovement(creature, creature.action().target());
        movement.move(deltaTime, creature.location());
    }

    private void action(float deltaTime, Creature creature, List<Creature> otherLives, Terrain terrain) {
        if(creature.action() != null && !creature.action().continueAction()) {
            creature.action(null);
        }
        if(creature.action() == null) {
            ActionDecisionService.ActionDecision decision = actionDecisionService.actionType(creature, otherLives);
            creature.desire(decision.actionType());

            ActionService actionService = actionFinderService.finderService(decision.actionType());
            Action action = actionService.findTarget(creature, decision.closest(), otherLives, terrain);
            creature.action(action);
        }
        creature.action().timeStep(deltaTime);
    }

    // Could potentially implement A* or other path-seeking algorithm
    private Movement calculateMovement(Creature creature, Location target) {
        Location location = creature.location();
        double distance = DistanceUtil.distance2D(location, target);


        double angle = Math.atan2(target.y() - location.y(), target.x() - location.x());

        Mobility mobility = creature.movementRange();
        double delta = Math.min(distance, mobility.speed());

        return new Movement(Math.cos(angle) * delta,
                Math.sin(angle) * delta,
                0d);
    }

    @Data
    @Accessors(fluent = true)
    @AllArgsConstructor
    private class Movement {
        private double x;
        private double y;
        private double z;

        public void move(float deltaTime, Location currLocation) {
            currLocation.x(
                    currLocation.x() + deltaTime * (float)x
            );
            currLocation.y(
                    currLocation.y() + deltaTime * (float)y
            );
            currLocation.z(
                    currLocation.z() + deltaTime * (float)z
            );
            drawBox.adjust(currLocation);
        }
    }

}
