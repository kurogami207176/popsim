package com.alaindroid.popsim.model;

import com.alaindroid.popsim.model.features.*;

import java.util.List;

public interface Life {
    String name();
    Mobility movementRange();
    Reach reach();
    Vision vision();
    Body body();
    List<Trait> traits();

    List<Trait> foods();
}
