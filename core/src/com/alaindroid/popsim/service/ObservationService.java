package com.alaindroid.popsim.service;

import com.alaindroid.popsim.model.Creature;
import com.alaindroid.popsim.model.Terrain;
import com.alaindroid.popsim.model.stats.CreatureSnapshot;
import com.alaindroid.popsim.util.DistanceUtil;

import java.util.Comparator;
import java.util.List;

public class ObservationService {
    public void observe(List<Creature> allCreatures, Terrain terrain) {
        for(int i = 0; i < allCreatures.size(); i++) {
            for(int j = i; j < allCreatures.size(); j++) {
                Creature iCreat = allCreatures.get(i);
                Creature jCreat = allCreatures.get(j);

                remember(iCreat);
                remember(jCreat);

                double distance = DistanceUtil.distance2D(iCreat.location(), jCreat.location());
                observe(iCreat, jCreat, distance);
                observe(jCreat, iCreat, distance);
            }
        }
        imposeMindLimit(allCreatures);
    }

    private void remember(Creature creature) {
        creature.memory().memory().clear();
        creature.memory().memory().addAll(creature.observation().observedCreatures());
    }
    private void observe(Creature observer, Creature observed, double distance) {
        if (observer.vision().length() >= distance) {
            observer.observation().observedCreatures().add(new CreatureSnapshot(observed, distance));
        }
    }
    private void imposeMindLimit(List<Creature> allCreatures){
        for(Creature creature: allCreatures) {
            creature.observation().observedCreatures().sort(Comparator.comparing(CreatureSnapshot::distance));
            creature.observation().observedCreatures(
                    limitList(creature.observation().observedCreatures(), creature.mind().observationLimit())
            );

            creature.memory().memory().sort(Comparator.comparing(CreatureSnapshot::timeSeen).reversed());
            creature.memory().memory(
                    limitList(creature.memory().memory(), creature.mind().memoryLimit())
            );
        }
    }

    private <T> List<T> limitList(List<T> anyCollection, int limit) {
        return anyCollection.subList(0, Math.min(limit, anyCollection.size()));
    }
}
