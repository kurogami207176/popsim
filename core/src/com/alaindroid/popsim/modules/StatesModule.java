package com.alaindroid.popsim.modules;

import com.alaindroid.popsim.draw.DrawBox;
import com.alaindroid.popsim.factory.CreatureDrawGenerator;
import com.alaindroid.popsim.factory.PopulationGenerator;
import com.alaindroid.popsim.model.Terrain;
import com.alaindroid.popsim.service.CreaturesService;
import com.alaindroid.popsim.states.MainSimulation;
import dagger.Module;
import dagger.Provides;

import javax.inject.Named;
import javax.inject.Singleton;

@Module
public class StatesModule {

    @Provides
    @Singleton
    MainSimulation mainSimulation(@Named("simSpeed") float simSpeed,
                                  CreatureDrawGenerator creatureDrawGenerator,
                                  DrawBox drawBox,
                                  PopulationGenerator populationGenerator,
                                  Terrain terrain,
                                  CreaturesService creaturesService) {
        return new MainSimulation(simSpeed, creatureDrawGenerator, drawBox, populationGenerator, terrain,
                creaturesService);
    }
}
