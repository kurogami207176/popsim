package com.alaindroid.popsim.model.features;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(fluent = true)
@AllArgsConstructor
public class Location {
    private float x;
    private float y;
    private float z;
}
