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
public class FoodSeekerService implements ActionService {

    private WanderService wanderService;

    @Override
    public Action findTarget(Creature creature, Optional<CreatureSnapshot> closest, List<CreatureSnapshot> otherLives, Terrain terrain) {
        return closest
                .map(target -> createAction(creature, target))
                .orElse(wanderService.findTarget(creature, closest, otherLives, terrain));
    }

    private Action createAction(Creature thisCreature, CreatureSnapshot target) {
        return new Action(ActionType.FIND_FOOD,
                () -> target.location(),
                deltaTime -> thisCreature.hunger().expend(deltaTime),
                () -> thisCreature.body().alive() && !thisCreature.reach().canReach(thisCreature.location(), target.location())
        );
    }

}