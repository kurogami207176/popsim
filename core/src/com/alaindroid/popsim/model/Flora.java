package com.alaindroid.popsim.model;

import com.alaindroid.popsim.model.features.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Arrays;
import java.util.List;

import static com.alaindroid.popsim.model.features.BaseFeatures.BASE_LONG_RANGE_REPRODUCTION;
import static com.alaindroid.popsim.model.features.BaseFeatures.BASE_MIND;
import static com.alaindroid.popsim.model.features.Trait.*;

@Data
@Accessors(fluent = true)
@RequiredArgsConstructor
public class Flora implements Life{
    private final String name;
    private Mobility movementRange = BaseFeatures.NO_MOTION;
    private Reach reach = BaseFeatures.NO_REACH;
    private final Vision vision = BaseFeatures.NO_VISION;
    private Body body = BaseFeatures.BASE_BODY.get();
    private final Satiaty satiaty = BaseFeatures.BASE_SATIATY;
    private final Mind mind = BASE_MIND;
    private Reproduction reproduction = BASE_LONG_RANGE_REPRODUCTION;
    private List<Trait> traits = Arrays.asList(Trait.PLANT);
    private List<Trait> foods = Arrays.asList(SUNLIGHT, LAND, AIR, WATER);
}