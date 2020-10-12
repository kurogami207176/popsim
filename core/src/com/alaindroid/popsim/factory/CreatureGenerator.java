package com.alaindroid.popsim.factory;

import com.alaindroid.popsim.draw.DrawBox;
import com.alaindroid.popsim.model.Creature;
import com.alaindroid.popsim.model.CreatureType;
import com.alaindroid.popsim.model.Fauna;
import com.alaindroid.popsim.model.Flora;
import com.alaindroid.popsim.model.features.Hunger;
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
                Fauna fauna = new Fauna("generic_animal_" + counter++,
                        BASIC_MOTION,
                        BASIC_REACH);
                return new Creature(fauna,
                        randomLocation(),
                        type,
                        new Hunger(fauna.body().health().currentHealth(),
                                fauna.body(),
                                fauna.satiaty())
                );
            case PLANT:
                Flora flora = new Flora("generic_plant_" + counter++);
                return new Creature(
                        flora,
                        randomLocation(),
                        type,
                        new Hunger(flora.body().health().currentHealth(),
                                flora.body(),
                                flora.satiaty())
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
