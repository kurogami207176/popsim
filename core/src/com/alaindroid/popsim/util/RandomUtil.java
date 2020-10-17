package com.alaindroid.popsim.util;

import com.alaindroid.popsim.model.features.Location;

import java.util.Random;

public class RandomUtil {
    private static Random random = new Random();

    public static float nextFloat() {
        return random.nextFloat();
    }
    public static float nextFloat(float flt) {
        return random.nextFloat() * flt;
    }
    public static float nextFloat(double flt) {
        return random.nextFloat() * (float) flt;
    }
    public static int nextInt() {
        return random.nextInt();
    }
    public static int nextInt(int number) {
        return random.nextInt(number);
    }
    public static int nextInt(int min, int max) {
        return min + random.nextInt(max - min);
    }
    public static float nextFloat(float min, float max) {
        return min + (max - min) * random.nextFloat();
    }

    public static Location randomPointInCircle(Location location, float radius) {
        float ranRad = nextFloat(radius);
        float ranAng = nextFloat(Math.PI);
        return new Location(
                location.x() + (float) Math.cos(ranAng) * ranRad,
                location.y() + (float) Math.sin(ranAng) * ranRad,
                location.z()
        );
    }
}
