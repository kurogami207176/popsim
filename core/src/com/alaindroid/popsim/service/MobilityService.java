package com.alaindroid.popsim.service;

import com.alaindroid.popsim.draw.DrawBox;
import com.alaindroid.popsim.model.Creature;
import com.alaindroid.popsim.model.features.Location;
import com.alaindroid.popsim.model.features.Mobility;
import com.alaindroid.popsim.service.seeker.TargetFinderService;
import com.alaindroid.popsim.util.DistanceUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class MobilityService {

    private final TargetDecisionService targetDecisionService;
    private final DrawBox drawBox;

    public void moveCreatures(float deltaTime, List<Creature> allLives) {
        for (int i = 0; i <allLives.size(); i++) {
            Creature thisCreature = allLives.get(i);
            if(!this.alive()  !thisCreature.movementRange().canMove()) {
                continue;
            }
            System.out.println("* " + thisCreature.name() + " - " + thisCreature.location());
            List<Creature> otherCreatures = new ArrayList<>(allLives);
            otherCreatures.remove(i);
            move(deltaTime, thisCreature, otherCreatures);
            System.out.println(">>" + thisCreature.name() + " - " + thisCreature.location());
        }
    }

    public void move(float deltaTime, Creature creature, List<Creature> otherLives) {
        if(creature.target() != null && creature.reach().canReach(creature.location(), creature.target())) {
            creature.target(null);
        }
        if(creature.target() == null) {
            TargetFinderService targetFinderService = targetDecisionService.findTarget(creature, otherLives);
            Location targetLocation = targetFinderService.findTarget(creature, otherLives);
            creature.target(targetLocation);
        }
        Movement movement = calculateMovement(creature, creature.target());

        movement.move(deltaTime, creature.location());
    }

    // Could potentially implement A* or other path-seeking algorithm
    private Movement calculateMovement(Creature creature, Location target) {
        Location location = creature.location();
        double distance = DistanceUtil.distance2D(location, target);

        double angle = Math.atan2(target.y() - location.y(), target.x() - location.x());

        Mobility mobility = creature.movementRange();

        return new Movement(Math.cos(angle) * mobility.speed(),
                Math.sin(angle) * mobility.speed(),
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
