package com.alaindroid.popsim.util;

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
    public static float nextInt() {
        return random.nextInt();
    }
    public static float nextInt(int number) {
        return random.nextInt(number);
    }
    public static float nextInt(int min, int max) {
        return min + random.nextInt(max - min);
    }
    public static float nextFloat(float min, float max) {
        return min + (max - min) * random.nextFloat();
    }
}
