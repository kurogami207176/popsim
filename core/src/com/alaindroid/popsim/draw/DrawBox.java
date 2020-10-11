package com.alaindroid.popsim.draw;

import com.alaindroid.popsim.model.features.Location;
import com.alaindroid.popsim.util.RandomUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(fluent = true)
@AllArgsConstructor
public class DrawBox {
    private float minX;
    private float maxX;
    private float minY;
    private float maxY;

    public void adjust(float minX, float maxX, float minY, float maxY) {
        this.minX = minX;
        this.maxX = maxX;
        this.minY = minY;
        this.maxY = maxY;
    }

    public void adjust(Location location) {
        if (location.x() < minX) location.x(maxX);
        if (location.x() > maxX) location.x(minX);
        if (location.y() < minY) location.y(maxY);
        if (location.y() > maxY) location.y(minY);
    }
    public void clip(Location location) {
        if (location.x() < minX) location.x(minX);
        if (location.x() > maxX) location.x(maxX);
        if (location.y() < minY) location.y(minY);
        if (location.y() > maxY) location.y(maxY);
    }

    public Location randomLocation(float z) {
        return new Location(
                RandomUtil.nextFloat(minX(), maxX()),
                RandomUtil.nextFloat(minY(), maxY()),
                z
        );
    }
}
