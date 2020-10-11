package com.alaindroid.popsim.factory;

import com.alaindroid.popsim.model.Creature;
import com.alaindroid.popsim.model.CreatureType;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@RequiredArgsConstructor
public class PopulationGenerator {

    @Getter
    @Accessors(fluent = true)
    private final CreatureGenerator creatureGenerator;

    public List<Creature> generateCreatures(Map<CreatureType, Integer> creaturesCount) {
        return creaturesCount.keySet().stream()
                .map(creatureType -> IntStream.range(0, creaturesCount.get(creatureType))
                            .mapToObj(i -> creatureGenerator.create(creatureType))
                            .collect(Collectors.toList()) )
                .flatMap(List::stream)
                .collect(Collectors.toList());
    }
}
