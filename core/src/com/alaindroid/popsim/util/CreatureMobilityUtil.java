package com.alaindroid.popsim.util;

import com.alaindroid.popsim.model.Creature;
import com.alaindroid.popsim.model.stats.CreatureSnapshot;

public class CreatureMobilityUtil {

    public static boolean canReachByJump(Creature creature, CreatureSnapshot otherLife) {
        return otherLife.location().z() <= creature.location().z() + creature.movementRange().jump();
    }

}
