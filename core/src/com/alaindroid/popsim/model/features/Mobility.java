package com.alaindroid.popsim.model.features;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;

@Data
@Accessors(fluent = true)
@RequiredArgsConstructor
public class Mobility {
    private final float speed;
    private final float jump;

    public static Mobility NO_MOTION = new Mobility(0,0);
    public static Mobility BASIC_MOTION = new Mobility(10,0);

    public boolean canMove() {
        return speed > 0 || jump > 0;
    }
}
