package com.alaindroid.popsim.model.stats;

import com.alaindroid.popsim.model.Creature;
import com.alaindroid.popsim.model.CreatureType;
import com.alaindroid.popsim.model.features.Location;
import com.alaindroid.popsim.model.features.Trait;
import lombok.Getter;
import lombok.experimental.Accessors;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Accessors(fluent = true)
public class CreatureSnapshot {
    private CreatureType type;
    private Location location;
    private boolean alive;
    private List<Trait> traits;
    private double distance;
    private long timeSeen;
    private Creature creatureReference;
    public CreatureSnapshot(Creature creature, double distance) {
        this.type = creature.type();
        this.location = creature.location();
        this.alive = creature.body().alive();
        this.timeSeen = System.currentTimeMillis();
        this.traits = creature.traits().stream().filter(Trait::visible).collect(Collectors.toList());
        this.distance = distance;

        this.creatureReference = creature;
    }
}