package com.alaindroid.popsim.service.seeker;

import com.alaindroid.popsim.model.Creature;
import com.alaindroid.popsim.model.Terrain;
import com.alaindroid.popsim.model.action.Action;
import com.alaindroid.popsim.model.stats.CreatureSnapshot;

import java.util.List;
import java.util.Optional;

public interface ActionService {
    Action findTarget(Creature creature, Optional<CreatureSnapshot> closest, List<CreatureSnapshot> otherLives, Terrain terrain);
}
