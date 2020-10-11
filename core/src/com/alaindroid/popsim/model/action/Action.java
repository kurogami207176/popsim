package com.alaindroid.popsim.model.action;

import com.alaindroid.popsim.model.features.Location;
import lombok.AllArgsConstructor;
import lombok.experimental.Accessors;

import java.util.function.Consumer;
import java.util.function.Supplier;

@Accessors(fluent = true)
@AllArgsConstructor
public class Action {
    private ActionType actionType;
    private Supplier<Location> target;
    private Consumer<Float> timeStep;
    private Supplier<Boolean> continueAction;

    public ActionType actionType() {
        return actionType;
    }

    public Location target() {
        return target.get();
    }
    public boolean continueAction() {
        return continueAction.get();
    }
    public void timeStep(float deltaTime) {
        timeStep.accept(deltaTime);
    }

}
