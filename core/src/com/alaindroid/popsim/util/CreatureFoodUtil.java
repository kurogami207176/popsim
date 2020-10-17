package com.alaindroid.popsim.util;

import com.alaindroid.popsim.model.Creature;
import com.alaindroid.popsim.model.Terrain;
import com.alaindroid.popsim.model.stats.CreatureSnapshot;

public class CreatureFoodUtil {

    public static boolean isEdible(Creature creature, CreatureSnapshot otherLife) {
        return otherLife.traits().stream()
                .anyMatch(creature.foods()::contains);
    }

    public static boolean isEdible(Creature creature, Terrain terrain) {
        return terrain.foods().stream()
                .anyMatch(creature.foods()::contains);
    }
}
