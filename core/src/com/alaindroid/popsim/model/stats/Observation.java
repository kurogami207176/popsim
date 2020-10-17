package com.alaindroid.popsim.model.stats;

import com.alaindroid.popsim.model.Terrain;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.List;

@Data
@Accessors(fluent = true)
@RequiredArgsConstructor
public class Observation {
    private List<CreatureSnapshot> observedCreatures = new ArrayList<>();
    private List<Terrain> terrain = new ArrayList<>();
}
