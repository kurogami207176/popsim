package com.alaindroid.popsim.util;

import com.alaindroid.popsim.model.features.Location;

public class DistanceUtil {
    public static double distance3D(Location oLoc, Location tLoc) {
        return Math.sqrt(
                Math.pow(tLoc.x() - oLoc.x(), 2) +
                        Math.pow(tLoc.y() - oLoc.y(), 2) +
                        Math.pow(tLoc.z() - oLoc.z(), 2)
        );
    }
    public static double distance2D(Location oLoc, Location tLoc) {
        return Math.sqrt(
                Math.pow(tLoc.x() - oLoc.x(), 2) +
                        Math.pow(tLoc.y() - oLoc.y(), 2)
        );
    }
}
