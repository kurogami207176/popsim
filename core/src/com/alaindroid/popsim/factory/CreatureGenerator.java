package com.alaindroid.popsim.factory;

import com.alaindroid.popsim.draw.DrawBox;
import com.alaindroid.popsim.model.Creature;
import com.alaindroid.popsim.model.CreatureType;
import com.alaindroid.popsim.model.Fauna;
import com.alaindroid.popsim.model.Flora;
import com.alaindroid.popsim.model.features.Location;
import lombok.RequiredArgsConstructor;

import java.util.Random;

import static com.alaindroid.popsim.model.features.Mobility.BASIC_MOTION;
import static com.alaindroid.popsim.model.features.Reach.BASIC_REACH;

@RequiredArgsConstructor
public class CreatureGenerator {

    private static int counter = 0;

    private final DrawBox drawBox;
    private Random random = new Random();

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
                drawBox.minX() + ((drawBox.maxX() - drawBox.minX()) * random.nextFloat()),
                drawBox.minY() + ((drawBox.maxY() - drawBox.minY()) * random.nextFloat()),
                0
        );
    }

}
