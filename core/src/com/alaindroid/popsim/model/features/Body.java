package com.alaindroid.popsim.model.features;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(fluent = true)
@AllArgsConstructor
public class Body {
    private int bodyCount; // Number of creatures in this unit. 1 for most animals, large numbers for smaller creatures.
    private Health health;

    @Data
    @Accessors(fluent = true)
    @AllArgsConstructor
    public static class Health {
        private float currentHealth;
        private float maxHealth;

    }

    public boolean alive() {
        return health.currentHealth > 0;
    }

    public float healthPercent() {
        return health().currentHealth() / health().maxHealth();
    }
}
