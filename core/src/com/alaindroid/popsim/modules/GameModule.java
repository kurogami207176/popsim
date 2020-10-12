package com.alaindroid.popsim.modules;

import com.alaindroid.popsim.model.Terrain;
import com.alaindroid.popsim.model.features.BaseFeatures;
import dagger.Module;
import dagger.Provides;

import javax.inject.Named;
import javax.inject.Singleton;

@Module
public class GameModule {

    @Provides
    @Singleton
    @Named("simSpeed")
    public float simSpeed() {
        return 2f;
    }

    @Provides
    public Terrain terrain() {
        return BaseFeatures.BASE_TERRAIN;
    }
}
