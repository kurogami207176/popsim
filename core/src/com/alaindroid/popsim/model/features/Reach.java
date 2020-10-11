package com.alaindroid.popsim.model.features;

import com.alaindroid.popsim.util.DistanceUtil;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;

@Data
@Accessors(fluent = true)
@RequiredArgsConstructor
public class Reach {
    private final float reach;
    private final float up;

    public boolean canReach(Location myLocation, Location otherLocation) {
        return reach >= DistanceUtil.distance2D(myLocation, otherLocation);
    }

}
