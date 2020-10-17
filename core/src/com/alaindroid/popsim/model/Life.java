package com.alaindroid.popsim.model;

import com.alaindroid.popsim.model.features.*;

import java.util.List;

public interface Life {
    String name();
    Mobility movementRange();
    Reach reach();
    Vision vision();
    Body body();
    Mind mind();
    Satiaty satiaty();

    List<Trait> traits();

    Reproduction reproduction();

    List<Trait> foods();
}
