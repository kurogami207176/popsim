package com.alaindroid.popsim.service.seeker;

import com.alaindroid.popsim.draw.DrawBox;
import com.alaindroid.popsim.model.Creature;
import com.alaindroid.popsim.model.Terrain;
import com.alaindroid.popsim.model.action.Action;
import com.alaindroid.popsim.model.action.ActionType;
import com.alaindroid.popsim.model.features.Location;
import com.alaindroid.popsim.util.RandomUtil;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
public class WanderService implements ActionService {
    private DrawBox drawBox;

    @Override
    public Action findTarget(Creature creature, Optional<Creature> closest, List<Creature> otherLives, Terrain terrain) {
        float angT = RandomUtil.nextFloat(Math.PI);
        float ranL = RandomUtil.nextFloat(-creature.vision().length(), creature.vision().length());
        Location location = creature.location();
        float xT = location.x() + (float) Math.cos(angT) * ranL;
        float yT = location.y() + (float) Math.sin(angT) * ranL;
        Location target = new Location(xT, yT, creature.location().z());
        drawBox.clip(target);
        return new Action(ActionType.WANDER,
                () -> target,
                deltaTime -> creature.hunger().expend(deltaTime),
                () -> creature.body().alive() && !creature.reach().canReach(creature.location(), target)
                );
    }
}