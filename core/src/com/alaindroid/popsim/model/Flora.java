package com.alaindroid.popsim.model;

import com.alaindroid.popsim.model.features.Mobility;
import com.alaindroid.popsim.model.features.Reach;
import com.alaindroid.popsim.model.features.Trait;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Arrays;
import java.util.List;

import static com.alaindroid.popsim.model.features.Trait.*;

@Data
@Accessors(fluent = true)
@RequiredArgsConstructor
public class Flora implements Life{
    private final String name;
    private Mobility movementRange = Mobility.NO_MOTION;
    private Reach reach = Reach.NO_REACH;
    private boolean alive = true;
    private List<Trait> traits = Arrays.asList(Trait.PLANT);
    private List<Trait> foods = Arrays.asList(SUNLIGHT, LAND, AIR, WATER);
}