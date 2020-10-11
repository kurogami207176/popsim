package com.alaindroid.popsim.util;

import com.alaindroid.popsim.model.Creature;
import lombok.Value;
import lombok.experimental.Accessors;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class CreatureDistanceUtil {
    public static Optional<Creature> findClosestEdibleLivingCreature(Creature creature, List<Creature> otherLives){
        return otherLives.stream()
                .filter(otherLife -> CreatureFoodUtil.isEdible(creature, otherLife))
                .filter(otherLife -> CreatureMobilityUtil.canReachByJump(creature, otherLife))
                .filter(otherLife -> otherLife.body().alive())
                .map(o -> new Distance(creature, o))
                .filter(d -> canSee(creature, d))
                .sorted(Comparator.comparing(Distance::distance))
                .map(Distance::creature)
                .findFirst();
    }

    public static boolean canReach(Creature creature, Optional<Creature> otherLife) {
        return otherLife
                .map(target -> creature.reach().canReach(creature.location(), target.location()))
                .orElse(false);
    }

    public static boolean canSee(Creature creature, Distance distance) {
        return creature.vision().length() >= distance.distance();
    }

    @Value
    @Accessors(fluent = true)
    private static class Distance {
        private Creature creature;
        private double distance;
        public Distance(Creature origin, Creature thisLife) {
            this.creature = thisLife;
            this.distance = DistanceUtil.distance2D(origin.location(), thisLife.location());
        }
    }
}
