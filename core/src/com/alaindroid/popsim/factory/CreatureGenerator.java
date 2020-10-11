package com.alaindroid.popsim.factory;

import com.alaindroid.popsim.draw.DrawBox;
import com.alaindroid.popsim.model.Creature;
import com.alaindroid.popsim.model.CreatureType;
import com.alaindroid.popsim.model.Fauna;
import com.alaindroid.popsim.model.Flora;
import com.alaindroid.popsim.model.features.Location;
import com.alaindroid.popsim.util.RandomUtil;
import lombok.RequiredArgsConstructor;

import static com.alaindroid.popsim.model.features.BaseFeatures.BASIC_MOTION;
import static com.alaindroid.popsim.model.features.BaseFeatures.BASIC_REACH;

@RequiredArgsConstructor
public class CreatureGenerator {

    private static int counter = 0;

    private final DrawBox drawBox;

    public Creature create(CreatureType type) {

        switch (type) {
            case ANIMAL:
                return new Creature(
                        new Fauna("generic_animal_" + counter++,
                                BASIC_MOTION,
                                BASIC_REACH
                                ),
                        randomLocation(),
                        type
                );
            case PLANT:
                return new Creature(
                        new Flora("generic_plant_" + counter++),
                        randomLocation(),
                        type
                );
        }
        return null;
    }

    private Location randomLocation() {
        return new Location(
                RandomUtil.nextFloat(drawBox.minX(), drawBox.maxX()),
                RandomUtil.nextFloat(drawBox.minY(), drawBox.maxY()),
                0
        );
    }

}
