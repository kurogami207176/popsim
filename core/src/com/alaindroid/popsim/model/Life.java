package com.alaindroid.popsim.model;

import com.alaindroid.popsim.model.features.Mobility;
import com.alaindroid.popsim.model.features.Reach;
import com.alaindroid.popsim.model.features.Trait;

import java.util.List;

public interface Life {
    String name();
    Mobility movementRange();
    Reach reach();
    boolean alive();
    List<Trait> traits();

    List<Trait> foods();
}
