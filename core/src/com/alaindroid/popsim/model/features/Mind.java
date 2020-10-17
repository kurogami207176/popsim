package com.alaindroid.popsim.model.features;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;

@Data
@Accessors(fluent = true)
@RequiredArgsConstructor
public class Mind {
    private final int observationLimit;
    private final int memoryLimit;
}
