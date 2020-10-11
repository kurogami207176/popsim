package com.alaindroid.popsim;

import com.alaindroid.popsim.draw.CreatureDraw;
import com.alaindroid.popsim.draw.DrawBox;
import com.alaindroid.popsim.factory.CreatureDrawGenerator;
import com.alaindroid.popsim.factory.CreatureGenerator;
import com.alaindroid.popsim.factory.PopulationGenerator;
import com.alaindroid.popsim.model.Creature;
import com.alaindroid.popsim.model.Terrain;
import com.alaindroid.popsim.model.features.BaseFeatures;
import com.alaindroid.popsim.service.ActionDecisionService;
import com.alaindroid.popsim.service.ActionFinderService;
import com.alaindroid.popsim.service.MobilityService;
import com.alaindroid.popsim.service.seeker.DeadActionService;
import com.alaindroid.popsim.service.seeker.EatService;
import com.alaindroid.popsim.service.seeker.FoodSeekerService;
import com.alaindroid.popsim.service.seeker.WanderService;
import com.alaindroid.popsim.states.MainSimulation;
import com.alaindroid.popsim.states.StateTypes;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import java.util.List;

public class MainGame extends ApplicationAdapter {

	StateTypes stateTypes = StateTypes.SIM;
	float simSpeed = 1f;

	CreatureDrawGenerator creatureDrawGenerator = new CreatureDrawGenerator();
	DrawBox drawBox = new DrawBox(0, 100, 0, 100);
	PopulationGenerator populationGenerator = new PopulationGenerator(new CreatureGenerator(drawBox));
	WanderService wanderService = new WanderService(drawBox);
	Terrain terrain = BaseFeatures.BASE_TERRAIN;
	MobilityService mobilityService = new MobilityService(new ActionDecisionService(),
			new ActionFinderService(new FoodSeekerService(wanderService),
					wanderService,
					new EatService(wanderService),
					new DeadActionService()),
			drawBox);

	SpriteBatch batch;
	ShapeRenderer shapeRenderer;
	List<Creature> creatures;
	List<CreatureDraw> creatureDraws;

	MainSimulation mainSimulation = new MainSimulation(simSpeed, creatureDrawGenerator,
			drawBox, populationGenerator, wanderService, terrain, mobilityService,
			batch, shapeRenderer, creatures, creatureDraws);


	@Override
	public void create () {
		mainSimulation.create();
	}

	@Override
	public void render () {
		switch (stateTypes) {
			case MENU:
				break;
			case SIM:
				mainSimulation.render();
				break;
			case END:
				break;
		}
	}
	
	@Override
	public void dispose () {
		mainSimulation.dispose();
	}

}
