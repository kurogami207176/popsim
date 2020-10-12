package com.alaindroid.popsim.model.features;

import com.alaindroid.popsim.util.RandomUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(fluent = true)
@AllArgsConstructor
public class Satiaty {
    private float digestEfficiency;
    private float eatRate;
    private float hungerLevel;
    private float fullnessLevel;
    private float overIndulgence;

    public float findFullnessLevel() {
        return fullnessLevel + RandomUtil.nextFloat(overIndulgence);
    }
}
