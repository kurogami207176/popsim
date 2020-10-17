package com.alaindroid.popsim.service.seeker;

import com.alaindroid.popsim.model.Creature;
import com.alaindroid.popsim.model.Terrain;
import com.alaindroid.popsim.model.action.Action;
import com.alaindroid.popsim.model.action.ActionType;
import com.alaindroid.popsim.model.stats.CreatureSnapshot;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
public class DeadActionService implements ActionService {
    @Override
    public Action findTarget(Creature creature, Optional<CreatureSnapshot> closest, List<CreatureSnapshot> otherLives, Terrain terrain) {
        return new Action(ActionType.DEAD,
                () -> creature.location(),
                deltaTime -> {},
                () -> !creature.body().alive()
        );
    }
}