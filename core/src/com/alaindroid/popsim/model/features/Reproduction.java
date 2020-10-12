package com.alaindroid.popsim.model.features;

import com.alaindroid.popsim.util.RandomUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(fluent = true)
@AllArgsConstructor
public class Reproduction {
    private float reproductionHealthLevel;
    private float spawnRange;
    private float probability;

    public boolean throwDice(Hunger level) {
        return RandomUtil.nextFloat() < probability;
    }
}
