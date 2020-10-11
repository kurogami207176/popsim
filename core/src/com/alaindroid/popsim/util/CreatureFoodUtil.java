package com.alaindroid.popsim.util;

import com.alaindroid.popsim.model.Creature;

public class CreatureFoodUtil {

    public static boolean isEdible(Creature creature, Creature otherLife) {
        return otherLife.traits().stream()
                .anyMatch(creature.foods()::contains);
    }
}
