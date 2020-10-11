package com.alaindroid.popsim.factory;

import com.alaindroid.popsim.draw.CreatureDraw;
import com.alaindroid.popsim.model.Creature;
import com.alaindroid.popsim.model.CreatureType;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

public class CreatureDrawGenerator {

    @Setter
    private Texture animalTexture;
    @Setter
    private Texture plantTexture;

    public List<CreatureDraw> generateCreatures(List<Creature> creatures) {
        return creatures.stream().map( c ->
                createCreatureDraw(c))
                .collect(Collectors.toList());
    }

    private CreatureDraw createCreatureDraw(Creature c) {
        return new CreatureDraw(c,
                drawLevel(c.type()),
                texture(c.type()),
                color(c.type()),
                radius(c.type())
        );
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
    private Color color(CreatureType type) {
        switch (type) {
            case ANIMAL:
                return Color.BROWN;
            case PLANT:
                return Color.GREEN;
        }
        return null;
    }
    private float radius(CreatureType type) {
        switch (type) {
            case ANIMAL:
                return 4;
            case PLANT:
                return 3;
        }
        return 0f;
    }
    private int drawLevel(CreatureType type) {
        switch (type) {
            case ANIMAL:
                return 10;
            case PLANT:
                return 1;
        }
        return 0;
    }

}
