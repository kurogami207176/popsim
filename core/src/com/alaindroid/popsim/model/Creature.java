package com.alaindroid.popsim.model;

import com.alaindroid.popsim.model.action.Action;
import com.alaindroid.popsim.model.action.ActionType;
import com.alaindroid.popsim.model.features.Hunger;
import com.alaindroid.popsim.model.features.Location;
import com.alaindroid.popsim.model.stats.Memory;
import com.alaindroid.popsim.model.stats.Observation;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;
import lombok.experimental.Delegate;

@Data
@Accessors(fluent = true)
@RequiredArgsConstructor
public class Creature {
    @Delegate
    private final Life life;
    private final Location location;
    private final CreatureType type;

    private final Hunger hunger;
    private Observation observation = new Observation();
    private Memory memory = new Memory();
    private ActionType desire;
    private Action action;
}
