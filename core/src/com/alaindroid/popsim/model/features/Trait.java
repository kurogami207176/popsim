package com.alaindroid.popsim.model.features;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Getter
@Accessors(fluent = true)
@NoArgsConstructor
@AllArgsConstructor
public enum Trait {
    ANIMAL,
    PLANT,
    SUNLIGHT,
    LAND,
    AIR,
    WATER;

    private boolean visible = true;
}
