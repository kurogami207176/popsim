package com.alaindroid.popsim.service;

import com.alaindroid.popsim.factory.CreatureGenerator;
import com.alaindroid.popsim.model.Creature;
import com.alaindroid.popsim.model.features.Location;
import com.alaindroid.popsim.model.features.Reproduction;
import com.alaindroid.popsim.util.RandomUtil;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class ReproductionService {

    private final CreatureGenerator creatureGenerator;

    public List<Creature> reproduce(Creature creature) {
        List<Creature> spawns = new ArrayList<>();
        if (creature.life().body().alive() && creature.reproduction().throwDice(creature.hunger())) {
            Reproduction reproduction = creature.reproduction();
            Location location = creature.location();
            int spawnCount = RandomUtil.nextInt(reproduction.minLitter(), reproduction.maxLitter());
            float required = reproduction.reproductionFullnessExcessLevel();
            for (int i = 0; i <spawnCount; i++) {
                Location spawnLocation = RandomUtil.randomPointInCircle(location, reproduction.spawnRange());
                Creature spawn = creatureGenerator.create(creature.type(), creature.name(), spawnLocation);
                creature.hunger().fullnessLevel(
                        Math.min(0, creature.hunger().fullnessLevel() - required)
                );
                System.out.println(creature.name() + " spawns " + spawn.name());
                spawns.add(spawn);
            }
        }
        return spawns;
    }
}
