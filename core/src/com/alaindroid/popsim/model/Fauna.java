package com.alaindroid.popsim.model;

import com.alaindroid.popsim.model.features.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Arrays;
import java.util.List;

import static com.alaindroid.popsim.model.features.BaseFeatures.BASE_SHORT_RANGE_REPRODUCTION;
import static com.alaindroid.popsim.model.features.Trait.PLANT;

@Data
@Accessors(fluent = true)
@RequiredArgsConstructor
public class Fauna implements Life {
    private final String name;
    private final Mobility movementRange;
    private final Reach reach;
    private final Vision vision = BaseFeatures.BASE_VISION;
    private final Body body = BaseFeatures.BASE_BODY.get();
    private final Satiaty satiaty = BaseFeatures.BASE_SATIATY;
    private final Reproduction reproduction = BASE_SHORT_RANGE_REPRODUCTION;
    private List<Trait> traits = Arrays.asList(Trait.ANIMAL);
    private List<Trait> foods = Arrays.asList(PLANT);

}
