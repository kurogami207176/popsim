package com.alaindroid.popsim.modules;

import com.alaindroid.popsim.draw.CreatureDraw;
import com.alaindroid.popsim.draw.DrawBox;
import com.alaindroid.popsim.factory.CreatureDrawGenerator;
import com.alaindroid.popsim.factory.PopulationGenerator;
import com.alaindroid.popsim.model.Creature;
import com.alaindroid.popsim.model.Terrain;
import com.alaindroid.popsim.service.MobilityService;
import com.alaindroid.popsim.service.seeker.WanderService;
import com.alaindroid.popsim.states.MainSimulation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import dagger.Module;
import dagger.Provides;

import javax.inject.Named;
import javax.inject.Singleton;
import java.util.List;

@Module
public class StatesModule {

    @Provides
    @Singleton
    MainSimulation mainSimulation(@Named("simSpeed") float simSpeed,
                                  CreatureDrawGenerator creatureDrawGenerator,
                                  DrawBox drawBox,
                                  PopulationGenerator populationGenerator,
                                  WanderService wanderService,
                                  Terrain terrain,
                                  MobilityService mobilityService) {
        return new MainSimulation(simSpeed, creatureDrawGenerator, drawBox, populationGenerator, wanderService, terrain,
                mobilityService);
    }
}
