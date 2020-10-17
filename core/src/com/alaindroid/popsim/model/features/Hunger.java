package com.alaindroid.popsim.model.features;

import com.alaindroid.popsim.model.Terrain;
import com.alaindroid.popsim.util.CreatureFoodUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Synchronized;
import lombok.experimental.Accessors;

import java.util.function.Supplier;

@Data
@Accessors(fluent = true)
@AllArgsConstructor
public class Hunger {
    private float fullnessLevel;
    private Body body;
    private Satiaty satiaty;

    public boolean isHungry() {
        return fullnessLevel < satiaty.hungerLevel();
    }

    public float excessFullness() {
        return Math.max(0, fullnessLevel - satiaty.fullnessLevel());
    }

    public void expend(float quantity) {
        if (fullnessLevel == 0) {
            body.health().currentHealth( Math.max(body.health().currentHealth() - quantity, 0) );
        }
        fullnessLevel = Math.max(fullnessLevel - quantity, 0);
    }

    public Supplier<Boolean> eatUntilFull(Body foodBody) {
        return () -> foodBody.alive() && fullnessLevel <= satiaty.findFullnessLevel();
    }

    public Supplier<Boolean> eatUntilFull(Terrain terrain) {
        return () -> fullnessLevel <= satiaty.findFullnessLevel();
    }

    @Synchronized
    public void eat(float deltaTime, Body otherBody) {
        float available = otherBody.health().currentHealth();
        float capacity = satiaty.eatRate() * deltaTime;
        float eatAmount = Math.min(available, capacity);
        otherBody.health().currentHealth(
                otherBody.health().currentHealth() - eatAmount
        );
        float healthGain = body.health().currentHealth() + (eatAmount * satiaty.digestEfficiency());
        body.health().currentHealth( Math.min(healthGain, body.health().maxHealth()) );
        fullnessLevel = fullnessLevel + eatAmount;
    }

    public void eat(float deltaTime) {
        float quantity = satiaty().eatRate() * deltaTime;
        if (body.health().currentHealth() < body.health().maxHealth()) {
            body.health().currentHealth( Math.min(body.health().currentHealth() + quantity, body.health().maxHealth()) );
        }
        else {
            fullnessLevel = fullnessLevel + quantity;
        }
    }

}
