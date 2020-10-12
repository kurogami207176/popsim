package com.alaindroid.popsim.modules;

public class DaggerInjectorModule {
    public static MainComponent get() {
        return DaggerMainComponent.builder().build();
    }
}
