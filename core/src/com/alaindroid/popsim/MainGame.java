package com.alaindroid.popsim;

import com.alaindroid.popsim.draw.DrawBox;
import com.alaindroid.popsim.factory.CreatureDrawGenerator;
import com.alaindroid.popsim.factory.CreatureGenerator;
import com.alaindroid.popsim.factory.PopulationGenerator;
import com.alaindroid.popsim.model.Terrain;
import com.alaindroid.popsim.model.features.BaseFeatures;
import com.alaindroid.popsim.modules.DaggerInjectorModule;
import com.alaindroid.popsim.modules.MainComponent;
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

import javax.inject.Inject;

public class MainGame extends ApplicationAdapter {

	StateTypes stateTypes = StateTypes.SIM;

	@Inject
	MainSimulation mainSimulation;

	public MainGame() {
		super();
		DaggerInjectorModule.get().inject(this);
	}

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
