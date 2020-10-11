package com.alaindroid.popsim.model.features;

import com.alaindroid.popsim.model.Terrain;

import java.util.Arrays;
import java.util.function.Supplier;

import static com.alaindroid.popsim.model.features.Trait.*;
import static com.alaindroid.popsim.model.features.Trait.WATER;

public class BaseFeatures {
    public static Mobility NO_MOTION = new Mobility(0,0);
    public static Mobility BASIC_MOTION = new Mobility(30,0);

    public static Reach NO_REACH = new Reach(0,0);
    public static Reach BASIC_REACH = new Reach(10,0);

    public static Supplier<Body.Health> BASE_HEALTH = () -> new Body.Health(100, 100);
    public static Body.Satiaty BASE_SATIATY = new Body.Satiaty(0.8f, 2f,0.3f, 0.7f, 0.1f);
    public static Supplier<Body> BASE_BODY = () -> new Body(1, BASE_HEALTH.get(), BASE_SATIATY);

    public static Terrain BASE_TERRAIN = new Terrain(Arrays.asList(SUNLIGHT, LAND, AIR, WATER));
    public static Vision BASE_VISION = new Vision(150f, (float)Math.PI / 4f); // 90
    public static Vision NO_VISION = new Vision(0, (float)Math.PI);

}
