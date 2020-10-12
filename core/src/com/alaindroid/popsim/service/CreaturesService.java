package com.alaindroid.popsim.service;

import com.alaindroid.popsim.model.Creature;
import com.alaindroid.popsim.model.Terrain;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class CreaturesService {

    private final MobilityService mobilityService;

    public void moveCreatures(float deltaTime, List<Creature> allLives, Terrain terrain) {
        for (int i = 0; i <allLives.size(); i++) {
            Creature thisCreature = allLives.get(i);
            List<Creature> otherCreatures = new ArrayList<>(allLives);
            otherCreatures.remove(i);

            mobilityService.move(deltaTime, thisCreature, otherCreatures, terrain);
        }
    }
}
