package com.alaindroid.popsim.service.seeker;

import com.alaindroid.popsim.model.Creature;
import com.alaindroid.popsim.model.features.Location;
import com.alaindroid.popsim.util.DistanceUtil;
import lombok.Value;
import lombok.experimental.Accessors;

import java.util.Comparator;
import java.util.List;

public class FoodSeekerService implements TargetFinderService {

    @Override
    public Location findTarget(Creature creature, List<Creature> otherLives) {
        return otherLives.stream()
                .filter(otherLife -> isEdible(creature, otherLife))
                .filter(otherLife -> canReachByJump(creature, otherLife))
                .map(o -> new Distance(creature, o))
                .sorted(Comparator.comparing(Distance::distance))
                .map(Distance::creature)
                .map(Creature::location)
                .findFirst()
                .orElse(null);
    }

    private boolean isEdible(Creature creature, Creature otherLife) {
        return otherLife.traits().stream()
                .anyMatch(creature.foods()::contains);
    }

    private boolean canReachByJump(Creature creature, Creature otherLife) {
        return otherLife.location().z() <= creature.movementRange().jump();
    }

    @Value
    @Accessors(fluent = true)
    private class Distance {
        private Creature creature;
        private double distance;
        public Distance(Creature origin, Creature thisLife) {
            this.creature = thisLife;
            this.distance = DistanceUtil.distance2D(origin.location(), thisLife.location());
        }
    }
}