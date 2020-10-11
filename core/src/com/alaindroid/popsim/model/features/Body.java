package com.alaindroid.popsim.model.features;

import com.alaindroid.popsim.util.RandomUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Synchronized;
import lombok.experimental.Accessors;

import java.util.function.Supplier;
import java.util.stream.Stream;

@Data
@Accessors(fluent = true)
@AllArgsConstructor
public class Body {
    private int bodyCount; // Number of creatures in this unit. 1 for most animals, large numbers for smaller creatures.
    private Health health;
    private Satiaty satiaty;

    @Data
    @Accessors(fluent = true)
    @AllArgsConstructor
    public static class Health {
        private float currentHealth;
        private float maxHealth;

    }

    @Data
    @Accessors(fluent = true)
    @AllArgsConstructor
    public static class Satiaty {
        private float digestEfficiency;
        private float eatRate;
        private float hungerLevelPercent;
        private float fullnessLevelPercent;
        private float overIndulgencePercent; // fullnessLevelPercent + overIndulgencePercent <= 100% or 1f

        public boolean isHungry(Health health) {
            return health.currentHealth < health.maxHealth() * hungerLevelPercent;
        }

        public float findFullnessLevel() {
            return Math.min(1f, fullnessLevelPercent + RandomUtil.nextFloat(overIndulgencePercent));
        }
    }

    public boolean isHungry() {
        return satiaty.isHungry(health);
    }

    public Supplier<Boolean> eatUntilFull(Body foodBody) {
        float fullPercent = satiaty.findFullnessLevel();
        float fullness = health().maxHealth() * fullPercent;
        System.out.println(health().currentHealth + " health to eat until " + fullness);
        return () -> foodBody.alive() &&
                health().currentHealth() <= fullness;
    }

    @Synchronized
    public void eat(float deltaTime, Body otherBody) {
        float available = otherBody.health().currentHealth();
        float needed = health().maxHealth - health().currentHealth;
        float capacity = satiaty.eatRate() * deltaTime;
        float eatAmount = Stream.of(available, needed, capacity)
                .reduce(Math::min)
                .orElse(deltaTime);
        otherBody.health().currentHealth(
                otherBody.health().currentHealth() - eatAmount
        );
        health().currentHealth(
                health.currentHealth() + (eatAmount * satiaty.digestEfficiency)
        );
    }

    public void eat(float deltaTime) {
        float quantity = satiaty().eatRate * deltaTime;
        health.currentHealth = Math.min(health.currentHealth + quantity, health.maxHealth);
    }

    public void expend(float quantity) {
        health.currentHealth = Math.max(health.currentHealth - quantity, 0);
    }

    public boolean alive() {
        return health.currentHealth > 0;
    }

    public float healthPercent() {
        return health().currentHealth() / health().maxHealth();
    }
}
