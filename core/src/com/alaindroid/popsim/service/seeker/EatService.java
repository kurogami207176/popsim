package com.alaindroid.popsim.service.seeker;

import com.alaindroid.popsim.model.Creature;
import com.alaindroid.popsim.model.Terrain;
import com.alaindroid.popsim.model.action.Action;
import com.alaindroid.popsim.model.action.ActionType;
import com.alaindroid.popsim.model.stats.CreatureSnapshot;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class EatService implements ActionService {

    private final WanderService wanderService;

    @Override
    public Action findTarget(Creature creature, Optional<CreatureSnapshot> closest, List<CreatureSnapshot> otherLives, Terrain terrain) {
        if (creature.movementRange().canMove()) {
            return closest
                    .map(target -> createAction(creature, target))
                    .orElse(wanderService.findTarget(creature, closest, otherLives, terrain));
        }
        else {
            return new Action(ActionType.EAT,
                    () -> creature.location(),
                    deltaTime -> creature.hunger().eat(deltaTime),
                    creature.hunger().eatUntilFull(terrain)
            );
        }
    }

    private Action createAction(Creature creature, CreatureSnapshot target) {
        return new Action(ActionType.EAT,
                () -> target.location(),
                deltaTime -> creature.hunger().eat(deltaTime, target.creatureReference().body()),
                creature.hunger().eatUntilFull(target.creatureReference().body())
        );
    }
}