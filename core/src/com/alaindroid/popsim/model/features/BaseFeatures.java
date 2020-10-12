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

    public static Supplier<Body.Health> BASE_HEALTH = () -> new Body.Health(70, 150);
    public static Satiaty BASE_SATIATY = new Satiaty(0.8f, 2f,30f, 70f, 10f);
    public static Supplier<Body> BASE_BODY = () -> new Body(1, BASE_HEALTH.get());

    public static Terrain BASE_TERRAIN = new Terrain(Arrays.asList(SUNLIGHT, LAND, AIR, WATER));
    public static Vision BASE_VISION = new Vision(150f, (float)Math.PI / 4f); // 90
    public static Vision NO_VISION = new Vision(0, (float)Math.PI);

    public static Reproduction BASE_SHORT_RANGE_REPRODUCTION = new Reproduction(100, 10, 0.01f);
    public static Reproduction BASE_LONG_RANGE_REPRODUCTION = new Reproduction(100, 100, 0.1f);

}
