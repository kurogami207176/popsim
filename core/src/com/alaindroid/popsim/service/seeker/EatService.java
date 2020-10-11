package com.alaindroid.popsim.service.seeker;

import com.alaindroid.popsim.model.Creature;
import com.alaindroid.popsim.model.Terrain;
import com.alaindroid.popsim.model.action.Action;
import com.alaindroid.popsim.model.action.ActionType;
import com.alaindroid.popsim.util.CreatureDistanceUtil;
import com.alaindroid.popsim.util.CreatureFoodUtil;
import com.alaindroid.popsim.util.CreatureMobilityUtil;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class EatService implements ActionService {

    private final WanderService wanderService;

    @Override
    public Action findTarget(Creature creature, Optional<Creature> closest, List<Creature> otherLives, Terrain terrain) {
        if (creature.movementRange().canMove()) {
            return closest
                    .map(target -> createAction(creature, target))
                    .orElse(wanderService.findTarget(creature, closest, otherLives, terrain));
        }
        else {
            return new Action(ActionType.EAT,
                    () -> creature.location(),
                    deltaTime -> creature.body().eat(deltaTime),
                    () -> creature.body().health().currentHealth() < creature.body().health().maxHealth()
            );
        }
    }

    private Action createAction(Creature creature, Creature target) {
        return new Action(ActionType.EAT,
                () -> target.location(),
                deltaTime -> creature.body().eat(deltaTime, target.body()),
                creature.body().eatUntilFull(target.body())
        );
    }
}