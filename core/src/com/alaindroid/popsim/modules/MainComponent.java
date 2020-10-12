package com.alaindroid.popsim.modules;

import com.alaindroid.popsim.MainGame;
import dagger.Component;

import javax.inject.Singleton;

@Singleton
@Component(modules = {ServiceModule.class, GeneratorModule.class, StatesModule.class, GameModule.class})
public interface MainComponent {
    void inject(MainGame mainGame);
}
