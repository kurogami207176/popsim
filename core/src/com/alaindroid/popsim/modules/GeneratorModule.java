package com.alaindroid.popsim.modules;

import com.alaindroid.popsim.draw.DrawBox;
import com.alaindroid.popsim.factory.CreatureDrawGenerator;
import com.alaindroid.popsim.factory.CreatureGenerator;
import com.alaindroid.popsim.factory.PopulationGenerator;
import dagger.Module;
import dagger.Provides;

import javax.inject.Singleton;

@Module
public class GeneratorModule {

    @Provides
    @Singleton
    public CreatureDrawGenerator creatureDrawGenerator() {
        return new CreatureDrawGenerator();
    }

    @Provides
    @Singleton
    public CreatureGenerator creatureGenerator(DrawBox drawBox) {
        return new CreatureGenerator(drawBox);
    }

    @Provides
    @Singleton
    public PopulationGenerator populationGenerator(CreatureGenerator creatureGenerator) {
        return new PopulationGenerator(creatureGenerator);
    }
}
