package com.alaindroid.popsim.model.features;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Map;

@Data
@Accessors(fluent = true)
@AllArgsConstructor
public class Body {
    private int bodyCount; // Number of creatures in this unit. 1 for most animals, large numbers for smaller creatures.
    private Map<LimbType, Health> limbHealth;

    @Data
    public static class Health {
        private float currentHealth;
        private float maxHealth;
    }

    enum LimbType {
        BODY, HEAD, ARM, LEG
    }
}
