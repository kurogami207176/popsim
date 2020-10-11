package com.alaindroid.popsim.util;

import com.alaindroid.popsim.model.Creature;
import lombok.Value;
import lombok.experimental.Accessors;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class CreatureDistanceUtil {
    public static Optional<Creature> findClosestEdibleLivingCreature(Creature creature, List<Creature> otherLives){
        return otherLives.stream()
                .filter(otherLife -> CreatureFoodUtil.isEdible(creature, otherLife))
                .filter(otherLife -> CreatureMobilityUtil.canReachByJump(creature, otherLife))
                .filter(otherLife -> otherLife.body().alive())
                .map(o -> new Distance(creature, o))
                .sorted(Comparator.comparing(Distance::distance))
                .map(Distance::creature)
                .findFirst();
    }

    public static boolean canReach(Creature creature, List<Creature> otherLives) {
        return findClosestEdibleLivingCreature(creature, otherLives)
                .map(target -> creature.reach().canReach(creature.location(), target.location()))
                .orElse(false);
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
