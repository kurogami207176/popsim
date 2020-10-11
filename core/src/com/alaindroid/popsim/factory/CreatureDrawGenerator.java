package com.alaindroid.popsim.factory;

import com.alaindroid.popsim.draw.CreatureDraw;
import com.alaindroid.popsim.model.Creature;
import com.alaindroid.popsim.model.CreatureType;
import com.badlogic.gdx.graphics.Texture;
import lombok.AllArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CreatureDrawGenerator {

    @Setter
    private Texture animalTexture;
    @Setter
    private Texture plantTexture;

    public List<CreatureDraw> generateCreatures(List<Creature> creatures) {
        return creatures.stream().map( c -> new CreatureDraw(c, texture(c.type())) )
                .collect(Collectors.toList());
    }

    private Texture texture(CreatureType type) {
        switch (type) {
            case ANIMAL:
                return animalTexture;
            case PLANT:
                return plantTexture;
        }
        return null;
    }
}
