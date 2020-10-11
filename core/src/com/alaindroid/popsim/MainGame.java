package com.alaindroid.popsim;

import com.alaindroid.popsim.draw.CreatureDraw;
import com.alaindroid.popsim.draw.DrawBox;
import com.alaindroid.popsim.factory.CreatureDrawGenerator;
import com.alaindroid.popsim.factory.CreatureGenerator;
import com.alaindroid.popsim.factory.PopulationGenerator;
import com.alaindroid.popsim.model.Creature;
import com.alaindroid.popsim.model.CreatureType;
import com.alaindroid.popsim.model.features.Mobility;
import com.alaindroid.popsim.service.MobilityService;
import com.alaindroid.popsim.service.TargetDecisionService;
import com.alaindroid.popsim.service.seeker.FoodSeekerService;
import com.alaindroid.popsim.service.seeker.WanderService;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainGame extends ApplicationAdapter {

	CreatureDrawGenerator creatureDrawGenerator = new CreatureDrawGenerator();
	DrawBox drawBox = new DrawBox(0, 100, 0, 100);
	PopulationGenerator populationGenerator = new PopulationGenerator(new CreatureGenerator(drawBox));
	MobilityService mobilityService = new MobilityService(new TargetDecisionService(new FoodSeekerService(), new WanderService()),
			drawBox);

	SpriteBatch batch;
	List<Creature> creatures;
	List<CreatureDraw> creatureDraws;


	@Override
	public void create () {
		batch = new SpriteBatch();
		Texture animalTexture = new Texture("brown-circle-16.png");
		Texture plantTexture = new Texture("green-circle-16.png");

		Map<CreatureType, Integer> counter = new HashMap<>();
		counter.put(CreatureType.ANIMAL, 10);
		counter.put(CreatureType.PLANT, 20);
		drawBox.adjust(0, Gdx.graphics.getWidth(), 0, Gdx.graphics.getHeight());

		creatureDrawGenerator.setAnimalTexture(animalTexture);
		creatureDrawGenerator.setPlantTexture(plantTexture);
		creatures = populationGenerator.generateCreatures(counter);
		creatureDraws = creatureDrawGenerator.generateCreatures(creatures);
		creatures.forEach(c -> System.out.println("++" + c.name() + " - " + c.location()));
	}

	@Override
	public void render () {
//		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClearColor(86 / 256, 125 / 256, 70 / 256, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		mobilityService.moveCreatures(Gdx.graphics.getDeltaTime(),
				creatures);
		batch.begin();
		creatureDraws.forEach( c -> batch.draw(c.img(), c.creature().location().x(), c.creature().location().y()) );
		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		creatureDraws.forEach( c -> c.img().dispose() );
	}
}
