package com.alaindroid.popsim;

import com.alaindroid.popsim.draw.CreatureDraw;
import com.alaindroid.popsim.draw.DrawBox;
import com.alaindroid.popsim.factory.CreatureDrawGenerator;
import com.alaindroid.popsim.factory.CreatureGenerator;
import com.alaindroid.popsim.factory.PopulationGenerator;
import com.alaindroid.popsim.model.Creature;
import com.alaindroid.popsim.model.CreatureType;
import com.alaindroid.popsim.model.Terrain;
import com.alaindroid.popsim.model.features.BaseFeatures;
import com.alaindroid.popsim.service.ActionDecisionService;
import com.alaindroid.popsim.service.ActionFinderService;
import com.alaindroid.popsim.service.MobilityService;
import com.alaindroid.popsim.service.seeker.EatService;
import com.alaindroid.popsim.service.seeker.FoodSeekerService;
import com.alaindroid.popsim.service.seeker.WanderService;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class MainGame extends ApplicationAdapter {

	CreatureDrawGenerator creatureDrawGenerator = new CreatureDrawGenerator();
	DrawBox drawBox = new DrawBox(0, 100, 0, 100);
	PopulationGenerator populationGenerator = new PopulationGenerator(new CreatureGenerator(drawBox));
	WanderService wanderService = new WanderService(drawBox);
	Terrain terrain = BaseFeatures.BASE_TERRAIN;
	MobilityService mobilityService = new MobilityService(new ActionDecisionService(),
			new ActionFinderService(new FoodSeekerService(wanderService), wanderService, new EatService(wanderService)),
			drawBox);

	SpriteBatch batch;
	ShapeRenderer shapeRenderer;
	List<Creature> creatures;
	List<CreatureDraw> creatureDraws;


	@Override
	public void create () {
		batch = new SpriteBatch();
		shapeRenderer = new ShapeRenderer();
		Texture animalTexture = new Texture("brown-circle-16.png");
		Texture plantTexture = new Texture("green-circle-16.png");

		Map<CreatureType, Integer> counter = new HashMap<>();
		counter.put(CreatureType.ANIMAL, 10);
		counter.put(CreatureType.PLANT, 20);
		drawBox.adjust(0, Gdx.graphics.getWidth(), 0, Gdx.graphics.getHeight());

		creatureDrawGenerator.setAnimalTexture(animalTexture);
		creatureDrawGenerator.setPlantTexture(plantTexture);
		creatures = populationGenerator.generateCreatures(counter);
		generateCreatureDraw();

		creatures.forEach(c -> System.out.println("++" + c.name() + " - " + c.location()));
	}

	@Override
	public void render () {
//		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClearColor(0, 0, 0, 1);
//		Gdx.gl.glClearColor(86f / 256, 125f / 256, 70f / 256, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		mobilityService.moveCreatures(Gdx.graphics.getDeltaTime(),
				creatures,
				terrain);
		batch.begin();
		creatureDraws.forEach( c -> c.draw(batch) );
		batch.end();
		creatureDraws.forEach( c -> c.draw(shapeRenderer) );
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		creatureDraws.forEach( c -> c.img().dispose() );
	}

	private void generateCreatureDraw() {
		creatureDraws = creatureDrawGenerator.generateCreatures(creatures);
		Map<Integer, List<CreatureDraw>> drawLevelCreature = creatureDraws
				.stream()
				.collect(Collectors.groupingBy(CreatureDraw::drawLevel, Collectors.toList()));
		creatureDraws = drawLevelCreature.keySet().stream()
				.sorted(Integer::compareTo)
				.map(drawLevelCreature::get)
				.flatMap(List::stream)
				.collect(Collectors.toList());
	}

}
