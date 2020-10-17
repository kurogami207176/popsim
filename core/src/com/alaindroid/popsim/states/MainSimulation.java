package com.alaindroid.popsim.states;

import com.alaindroid.popsim.draw.CreatureDraw;
import com.alaindroid.popsim.draw.DrawBox;
import com.alaindroid.popsim.factory.CreatureDrawGenerator;
import com.alaindroid.popsim.factory.PopulationGenerator;
import com.alaindroid.popsim.model.Creature;
import com.alaindroid.popsim.model.CreatureType;
import com.alaindroid.popsim.model.Terrain;
import com.alaindroid.popsim.service.CreaturesService;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import lombok.RequiredArgsConstructor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class MainSimulation {

    final float simSpeed;

    final CreatureDrawGenerator creatureDrawGenerator;
    final DrawBox drawBox;
    final PopulationGenerator populationGenerator;
    final Terrain terrain;
    final CreaturesService creaturesService;

    SpriteBatch batch;
    ShapeRenderer shapeRenderer;
    List<Creature> creatures;

    Texture animalTexture;
    Texture plantTexture;

    public void create () {
        batch = new SpriteBatch();
        shapeRenderer = new ShapeRenderer();

        animalTexture = new Texture("brown-circle-16.png");
        plantTexture = new Texture("green-circle-16.png");

        Map<CreatureType, Integer> counter = new HashMap<>();
        counter.put(CreatureType.ANIMAL, 10);
        counter.put(CreatureType.PLANT, 10);
        drawBox.adjust(0, Gdx.graphics.getWidth(), 0, Gdx.graphics.getHeight());

        creatureDrawGenerator.setAnimalTexture(animalTexture);
        creatureDrawGenerator.setPlantTexture(plantTexture);
        creatures = populationGenerator.generateCreatures(counter);
        generateCreatureDraw();

        creatures.forEach(c -> System.out.println("++" + c.name() + " - " + c.location()));
    }

    public void render () {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        creaturesService.moveCreatures(simSpeed * Gdx.graphics.getDeltaTime(),
                creatures,
                terrain);
        List<CreatureDraw> creatureDraws = generateCreatureDraw();
        batch.begin();
        creatureDraws.forEach( c -> c.draw(batch) );
        batch.end();
        creatureDraws.forEach( c -> c.draw(shapeRenderer) );
    }

    public void dispose () {
        batch.dispose();
        animalTexture.dispose();
        plantTexture.dispose();
    }

    private List<CreatureDraw> generateCreatureDraw() {
        List<CreatureDraw> creatureDraws = creatureDrawGenerator.generateCreatures(creatures);
        Map<Integer, List<CreatureDraw>> drawLevelCreature = creatureDraws
                .stream()
                .collect(Collectors.groupingBy(CreatureDraw::drawLevel, Collectors.toList()));
        return drawLevelCreature.keySet().stream()
                .sorted(Integer::compareTo)
                .map(drawLevelCreature::get)
                .flatMap(List::stream)
                .collect(Collectors.toList());
    }

}
