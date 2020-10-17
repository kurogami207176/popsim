package com.alaindroid.popsim.model.features;

import com.alaindroid.popsim.util.RandomUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(fluent = true)
@AllArgsConstructor
public class Reproduction {
    private float reproductionFullnessExcessLevel;
    private int minLitter;
    private int maxLitter;
    private float spawnRange;
    private float probability;

    public boolean throwDice(Hunger hunger) {
        return hunger.excessFullness() >= reproductionFullnessExcessLevel
                ? RandomUtil.nextFloat() < probability
                : false;
    }

    public float reproductionProgress(Hunger hunger) {
        return hunger.excessFullness() / reproductionFullnessExcessLevel;
    }
}
