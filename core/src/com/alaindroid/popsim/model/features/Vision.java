package com.alaindroid.popsim.model.features;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;

@Data
@Accessors(fluent = true)
@RequiredArgsConstructor
public class Vision {
    private final float length;
    private final float angleRadian;
}
