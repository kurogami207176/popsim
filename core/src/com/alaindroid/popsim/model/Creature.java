package com.alaindroid.popsim.model;

import com.alaindroid.popsim.model.features.Location;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;
import lombok.experimental.Delegate;

@Data
@Accessors(fluent = true)
@RequiredArgsConstructor
public class Creature {
    @Delegate
    private final Life life;
    private final Location location;
    private final CreatureType type;

    private Location target;
}
