package com.alaindroid.popsim;

import com.alaindroid.popsim.modules.DaggerInjectorModule;
import com.alaindroid.popsim.states.MainSimulation;
import com.alaindroid.popsim.states.StateTypes;
import com.badlogic.gdx.ApplicationAdapter;

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
