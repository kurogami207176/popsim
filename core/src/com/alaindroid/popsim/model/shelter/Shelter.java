package com.alaindroid.popsim.model.shelter;

import com.alaindroid.popsim.model.features.Location;
import com.alaindroid.popsim.util.RandomUtil;
import lombok.RequiredArgsConstructor;
import lombok.Value;

@Value
@RequiredArgsConstructor
public class Shelter {
    private Location location;
    private float sizeX;
    private float sizeY;

    public Location getPoint() {
        return new Location(
                RandomUtil.nextFloat(location.x() - sizeX, location.x() + sizeX),
                RandomUtil.nextFloat(location.y() - sizeY, location.y() + sizeY),
                location.z()
        );
    }
}
