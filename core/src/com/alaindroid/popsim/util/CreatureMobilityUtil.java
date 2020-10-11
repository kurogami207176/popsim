package com.alaindroid.popsim.util;

import com.alaindroid.popsim.model.Creature;

public class CreatureMobilityUtil {

    public static boolean canReachByJump(Creature creature, Creature otherLife) {
        return otherLife.location().z() <= creature.location().z() + creature.movementRange().jump();
    }

}
